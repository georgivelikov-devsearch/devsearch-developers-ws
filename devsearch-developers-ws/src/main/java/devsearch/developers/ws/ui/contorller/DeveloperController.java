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

import devsearch.developers.ws.client.ImageClient;
import devsearch.developers.ws.exception.RestApiDevelopersException;
import devsearch.developers.ws.service.DeveloperService;
import devsearch.developers.ws.shared.dto.DeveloperDto;
import devsearch.developers.ws.shared.dto.DeveloperListDto;
import devsearch.developers.ws.shared.utils.Mapper;
import devsearch.developers.ws.ui.model.request.DeveloperImageRequest;
import devsearch.developers.ws.ui.model.request.DeveloperRequest;
import devsearch.developers.ws.ui.model.response.DeveloperListResponse;
import devsearch.developers.ws.ui.model.response.DeveloperPublicResponse;
import devsearch.developers.ws.ui.model.response.DeveloperResponse;
import devsearch.developers.ws.ui.model.response.ImageResponse;

@RestController
@RequestMapping("developers")
public class DeveloperController {

    @Autowired
    private DeveloperService developerService;

    @Autowired
    private ImageClient imageClient;

    @Autowired
    private Mapper modelMapper;

    @GetMapping(path = "/status")
    public String status() {
	return "ProfileController is working!";
    }

    @GetMapping(path = "/user/{username}")
    public DeveloperResponse getDeveloper(@PathVariable String username, @AuthenticationPrincipal Jwt jwt)
	    throws RestApiDevelopersException {
	DeveloperDto developerDto = null;

	try {
	    developerDto = developerService.getDeveloperByUsername(username);
	} catch (RestApiDevelopersException ex) {
	    // Profile for the currently logged in user does not exist. Create new profile.
	    String preferredUsername = jwt.getClaimAsString("preferred_username");
	    String firstName = jwt.getClaimAsString("given_name");
	    String lastName = jwt.getClaimAsString("family_name");
	    String contactEmail = jwt.getClaimAsString("email");

	    developerDto = new DeveloperDto();
	    developerDto.setUsername(preferredUsername);
	    developerDto.setFirstName(firstName);
	    developerDto.setLastName(lastName);
	    developerDto.setContactEmail(contactEmail);
	    developerDto = developerService.createDeveloper(developerDto);
	}

	return modelMapper.map(developerDto, DeveloperResponse.class);
    }

    @GetMapping(path = "/public/user/{username}")
    public DeveloperPublicResponse getPublicDeveloper(@PathVariable String username) throws RestApiDevelopersException {
	DeveloperDto profileDto = developerService.getDeveloperByUsername(username);

	return modelMapper.map(profileDto, DeveloperPublicResponse.class);
    }

    @GetMapping("/public/all")
    public DeveloperListResponse getDeveloper(@RequestParam(value = "page", defaultValue = "1") int page,
	    @RequestParam(value = "limit", defaultValue = "6") int limit,
	    @RequestParam(value = "searchText", defaultValue = "") String searchText)
	    throws RestApiDevelopersException {

	// In the Repository implementation pagination starts with '0', but in UI
	// usually pages start from 1, 2, 3 etc. So UI will send the number of the page,
	// which should be reduced by 1
	if (page > 0) {
	    page -= 1;
	}

	DeveloperListDto developers = developerService.getDevelopers(page, limit, searchText);
	Collection<DeveloperPublicResponse> responseDevelopers = new ArrayList<DeveloperPublicResponse>();
	// TODO fix this
	String username = "Dummy";
	boolean senderFound = false;
	for (DeveloperDto developer : developers.getDevelopers()) {
	    DeveloperPublicResponse publicDeveloper = modelMapper.map(developer, DeveloperPublicResponse.class);
	    if (!senderFound && developer.getUsername().equals(username)) {
		publicDeveloper.setSender(true);
		senderFound = true;
	    }

	    responseDevelopers.add(publicDeveloper);
	}

	DeveloperListResponse response = new DeveloperListResponse();
	response.setTotalPages(developers.getTotalPages());
	response.setProfiles(responseDevelopers);

	return response;
    }

    @PostMapping(path = "/user/{username}")
    public DeveloperResponse createDeveloper(@RequestBody DeveloperRequest developerRequest)
	    throws RestApiDevelopersException {
	DeveloperDto developerDto = modelMapper.map(developerRequest, DeveloperDto.class);
	DeveloperDto createdDeveloper = developerService.createDeveloper(developerDto);

	return modelMapper.map(createdDeveloper, DeveloperResponse.class);
    }

    @PutMapping(path = "/user/{username}")
    public DeveloperResponse updateDeveloper(@RequestBody DeveloperRequest developerRequest)
	    throws RestApiDevelopersException {
	DeveloperDto developerDto = modelMapper.map(developerRequest, DeveloperDto.class);

	if (developerDto.isNewProfilePictureUpload()) {
	    DeveloperImageRequest imageRequest = new DeveloperImageRequest();
	    imageRequest.setDeveloperId(developerDto.getDeveloperId());
	    imageRequest.setDeveloperPictureBase64(developerDto.getProfilePictureBase64());

	    ResponseEntity<ImageResponse> imageResponse = imageClient.addDeveloperImage(imageRequest);
	    String profilePictureUrl = imageResponse.getBody().getDeveloperPictureUrl();
	    developerDto.setProfilePictureUrl(profilePictureUrl);
	}

	DeveloperDto updatedDeveloper = developerService.updateDeveloper(developerDto);

	return modelMapper.map(updatedDeveloper, DeveloperResponse.class);
    }

    @PostMapping("/initial")
    public ResponseEntity<String> initialSeed(@RequestBody List<DeveloperRequest> developerRequests)
	    throws RestApiDevelopersException {

	List<DeveloperDto> developersDto = new ArrayList<>();
	for (DeveloperRequest developerRequest : developerRequests) {
	    DeveloperDto developerDto = modelMapper.map(developerRequest, DeveloperDto.class);
	    developersDto.add(developerDto);
	}

	developerService.initialSeed(developersDto);

	return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }

    // TODO add delete mapping
}
