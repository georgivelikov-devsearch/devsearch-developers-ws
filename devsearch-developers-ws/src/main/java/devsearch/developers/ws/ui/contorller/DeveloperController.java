package devsearch.developers.ws.ui.contorller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import devsearch.common.exception.DevsearchApiException;
import devsearch.developers.ws.client.ImageClient;
import devsearch.developers.ws.client.ProjectsClient;
import devsearch.developers.ws.exception.ExceptionMessages;
import devsearch.developers.ws.security.jwt.JwtService;
import devsearch.developers.ws.service.CommentService;
import devsearch.developers.ws.service.DeveloperService;
import devsearch.developers.ws.shared.dto.CommentDto;
import devsearch.developers.ws.shared.dto.DeveloperDto;
import devsearch.developers.ws.shared.dto.DeveloperListDto;
import devsearch.developers.ws.shared.mapper.ModelMapper;
import devsearch.developers.ws.ui.model.request.CommentRequest;
import devsearch.developers.ws.ui.model.request.DeveloperImageRequest;
import devsearch.developers.ws.ui.model.request.DeveloperRequest;
import devsearch.developers.ws.ui.model.response.CommentResponse;
import devsearch.developers.ws.ui.model.response.DeveloperListResponse;
import devsearch.developers.ws.ui.model.response.DeveloperResponse;
import devsearch.developers.ws.ui.model.response.ImageResponse;
import devsearch.developers.ws.ui.model.response.ProjectResponse;

@RestController
@RequestMapping("developers")
public class DeveloperController {

    private static final String BEARER_PREFIX = "Bearer ";

    @Autowired
    private DeveloperService developerService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ImageClient imageClient;

    @Autowired
    private ProjectsClient projectsClient;

    @Autowired
    private ModelMapper mapper;

    @GetMapping(path = "/status")
    public String status() {
	return "DeveloperController is working!";
    }

    @GetMapping(path = "/user/{username}")
    public DeveloperResponse getDeveloper(@PathVariable String username, @AuthenticationPrincipal Jwt jwt)
	    throws DevsearchApiException {
	DeveloperDto developerDto = developerService.getDeveloperByUsername(username);
	if (developerDto == null) {
	    // Username url param may be 'undefined' because of refresh page. Check jwt
	    // username
	    String preferredUsername = jwtService.getUsername(jwt);
	    developerDto = developerService.getDeveloperByUsername(preferredUsername);

	    if (developerDto == null) {
		// Developer profile for the currently logged in user does not exist.
		// User is checking the profile for the first time ever.
		String firstName = jwtService.getFirstName(jwt);
		String lastName = jwtService.getLastName(jwt);
		String contactEmail = jwtService.getContactEmail(jwt);

		developerDto = new DeveloperDto();
		developerDto.setUsername(preferredUsername);
		developerDto.setFirstName(firstName);
		developerDto.setLastName(lastName);
		developerDto.setContactEmail(contactEmail);
		developerDto = developerService.createDeveloper(developerDto);
	    }
	}

	DeveloperResponse developerResponse = mapper.map(developerDto, DeveloperResponse.class);

	List<ProjectResponse> projects = projectsClient
		.getProjectsForDeveloper(developerResponse.getDeveloperId(), getAuthHeaderValue(jwt))
		.getBody();
	developerResponse.setProjects(projects);

	return developerResponse;
    }

    @GetMapping(path = "/public/user/{username}")
    public DeveloperResponse getPublicDeveloper(@PathVariable String username) throws DevsearchApiException {
	DeveloperDto developerDto = developerService.getDeveloperByUsername(username);

	if (developerDto == null) {
	    throw new DevsearchApiException(ExceptionMessages.NO_PFOFILE_FOUND_FOR_THIS_USER.toString());
	}

	DeveloperResponse developerResponse = mapper.map(developerDto, DeveloperResponse.class);

	List<ProjectResponse> projects = projectsClient
		.getProjectsForDeveloperByUsername(developerResponse.getUsername())
		.getBody();
	developerResponse.setProjects(projects);

	// Removing developerId from public data
	developerResponse.setDeveloperId(null);

	return developerResponse;
    }

    @GetMapping("/public/all")
    public DeveloperListResponse getDevelopers(@RequestParam(value = "page", defaultValue = "1") int page,
	    @RequestParam(value = "limit", defaultValue = "6") int limit,
	    @RequestParam(value = "searchText", defaultValue = "") String searchText) throws DevsearchApiException {

	// In the Repository implementation pagination starts with '0', but in UI
	// usually pages start from 1, 2, 3 etc. So UI will send the number of the page,
	// which should be reduced by 1
	if (page > 0) {
	    page -= 1;
	}

	DeveloperListDto developersDto = developerService.getDevelopers(page, limit, searchText);
	Collection<DeveloperResponse> developersResponse = new ArrayList<DeveloperResponse>();
	for (DeveloperDto developer : developersDto.getDevelopers()) {
	    DeveloperResponse developerResponse = mapper.map(developer, DeveloperResponse.class);
	    // Removing developerId from public data
	    developerResponse.setDeveloperId(null);
	    developersResponse.add(developerResponse);
	}

	DeveloperListResponse response = new DeveloperListResponse();
	response.setTotalPages(developersDto.getTotalPages());
	response.setDevelopers(developersResponse);

	return response;
    }

    @PostMapping(path = "/user/{username}")
    public DeveloperResponse createDeveloper(@RequestBody DeveloperRequest developerRequest)
	    throws DevsearchApiException {
	DeveloperDto developerDto = mapper.map(developerRequest, DeveloperDto.class);
	DeveloperDto createdDeveloper = developerService.createDeveloper(developerDto);

	return mapper.map(createdDeveloper, DeveloperResponse.class);
    }

    @PutMapping(path = "/user/{username}")
    public DeveloperResponse updateDeveloper(@RequestBody DeveloperRequest developerRequest)
	    throws DevsearchApiException {
	DeveloperDto developerDto = mapper.map(developerRequest, DeveloperDto.class);

	if (developerDto.isNewDeveloperPictureUpload()) {
	    DeveloperImageRequest imageRequest = new DeveloperImageRequest();
	    imageRequest.setDeveloperId(developerDto.getDeveloperId());
	    imageRequest.setDeveloperPictureBase64(developerDto.getDeveloperPictureBase64());

	    ResponseEntity<ImageResponse> imageResponse = imageClient.addDeveloperImage(imageRequest);
	    String developerPictureUrl = imageResponse.getBody().getDeveloperPictureUrl();
	    developerDto.setDeveloperPictureUrl(developerPictureUrl);
	}

	DeveloperDto updatedDeveloper = developerService.updateDeveloper(developerDto);

	return mapper.map(updatedDeveloper, DeveloperResponse.class);
    }

    @PostMapping("/comment")
    public CommentResponse commentProject(@RequestBody CommentRequest commentRequest) throws DevsearchApiException {
	CommentDto commentDto = mapper.map(commentRequest, CommentDto.class);
	CommentDto newCommentDto = commentService.createComment(commentDto);

	return mapper.map(newCommentDto, CommentResponse.class);
    }

    @GetMapping("/comments")
    public List<CommentResponse> commentsForProject(
	    @RequestParam(value = "projectId", defaultValue = "") String projectId) throws DevsearchApiException {

	List<CommentDto> commentsDto = commentService.getCommentsByProjectId(projectId);
	List<CommentResponse> responseList = new ArrayList<>();
	for (CommentDto commentDto : commentsDto) {
	    CommentResponse commentResponse = mapper.map(commentDto, CommentResponse.class);
	    responseList.add(commentResponse);
	}

	return responseList;
    }

    // TODO add delete mapping

    @PostMapping("/initial")
    public ResponseEntity<String> initialSeed(@RequestBody List<DeveloperRequest> developerRequests)
	    throws DevsearchApiException {

	List<DeveloperDto> developersDto = new ArrayList<>();
	for (DeveloperRequest developerRequest : developerRequests) {
	    DeveloperDto developerDto = mapper.map(developerRequest, DeveloperDto.class);
	    developersDto.add(developerDto);
	}

	developerService.initialSeed(developersDto);

	return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }

    private String getAuthHeaderValue(Jwt jwt) {
	return BEARER_PREFIX + jwt.getTokenValue();
    }

}
