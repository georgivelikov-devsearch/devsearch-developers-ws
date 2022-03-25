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
import devsearch.developers.ws.exception.RestApiProfilesException;
import devsearch.developers.ws.service.ProfileService;
import devsearch.developers.ws.shared.dto.ProfileDto;
import devsearch.developers.ws.shared.dto.ProfileListDto;
import devsearch.developers.ws.shared.utils.Mapper;
import devsearch.developers.ws.ui.model.request.ImageRequest;
import devsearch.developers.ws.ui.model.request.ProfileRequest;
import devsearch.developers.ws.ui.model.response.ImageResponse;
import devsearch.developers.ws.ui.model.response.ProfileListResponse;
import devsearch.developers.ws.ui.model.response.ProfilePublicResponse;
import devsearch.developers.ws.ui.model.response.ProfileResponse;

@RestController
@RequestMapping("profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ImageClient imageClient;

    @Autowired
    private Mapper modelMapper;

    @GetMapping(path = "/status")
    public String status() {
	return "ProfileController is working!";
    }

    @GetMapping(path = "/user/{username}")
    public ProfileResponse getProfile(@PathVariable String username, @AuthenticationPrincipal Jwt jwt)
	    throws RestApiProfilesException {
	ProfileDto profileDto = null;

	try {
	    profileDto = profileService.getProfileByUsername(username);
	} catch (RestApiProfilesException ex) {
	    // Profile for the currently logged in user does not exist. Create new profile.
	    String preferredUsername = jwt.getClaimAsString("preferred_username");
	    String firstName = jwt.getClaimAsString("given_name");
	    String lastName = jwt.getClaimAsString("family_name");
	    String contactEmail = jwt.getClaimAsString("email");

	    profileDto = new ProfileDto();
	    profileDto.setUsername(preferredUsername);
	    profileDto.setFirstName(firstName);
	    profileDto.setLastName(lastName);
	    profileDto.setContactEmail(contactEmail);
	    profileDto = profileService.createProfile(profileDto);
	}

	return modelMapper.map(profileDto, ProfileResponse.class);
    }

    @GetMapping(path = "/public/user/{username}")
    public ProfilePublicResponse getPublicProfile(@PathVariable String username) throws RestApiProfilesException {
	ProfileDto profileDto = profileService.getProfileByUsername(username);

	return modelMapper.map(profileDto, ProfilePublicResponse.class);
    }

    @GetMapping("/public/all")
    public ProfileListResponse getProfiles(@RequestParam(value = "page", defaultValue = "1") int page,
	    @RequestParam(value = "limit", defaultValue = "6") int limit,
	    @RequestParam(value = "searchText", defaultValue = "") String searchText) throws RestApiProfilesException {

	// In the Repository implementation pagination starts with '0', but in UI
	// usually pages start from 1, 2, 3 etc. So UI will send the number of the page,
	// which should be reduced by 1
	if (page > 0) {
	    page -= 1;
	}

	ProfileListDto profiles = profileService.getProfiles(page, limit, searchText);
	Collection<ProfilePublicResponse> responseProfiles = new ArrayList<ProfilePublicResponse>();
	String username = "Dummy";
	boolean senderFound = false;
	for (ProfileDto profile : profiles.getProfiles()) {
	    ProfilePublicResponse publicProfile = modelMapper.map(profile, ProfilePublicResponse.class);
	    if (!senderFound && profile.getUsername().equals(username)) {
		publicProfile.setSender(true);
		senderFound = true;
	    }

	    responseProfiles.add(publicProfile);
	}

	ProfileListResponse response = new ProfileListResponse();
	response.setTotalPages(profiles.getTotalPages());
	response.setProfiles(responseProfiles);

	return response;
    }

    @PostMapping(path = "/user/{username}")
    public ProfileResponse createProfile(@RequestBody ProfileRequest profileRequest) throws RestApiProfilesException {
	ProfileDto profileDto = modelMapper.map(profileRequest, ProfileDto.class);
	ProfileDto createdProfile = profileService.createProfile(profileDto);

	return modelMapper.map(createdProfile, ProfileResponse.class);
    }

    @PutMapping(path = "/user/{username}")
    public ProfileResponse updateProfile(@RequestBody ProfileRequest profileRequest) throws RestApiProfilesException {
	ProfileDto profileDto = modelMapper.map(profileRequest, ProfileDto.class);

	if (profileDto.isNewProfilePictureUpload()) {
	    ImageRequest imageRequest = new ImageRequest();
	    imageRequest.setProfilePictureBase64(profileDto.getProfilePictureBase64());
	    imageRequest.setProfileId(profileDto.getProfileId());

	    ResponseEntity<ImageResponse> imageResponse = imageClient.addProfileImage(imageRequest);
	    String profilePictureUrl = imageResponse.getBody().getProfilePictureUrl();
	    profileDto.setProfilePictureUrl(profilePictureUrl);
	}

	ProfileDto updatedProfile = profileService.updateProfile(profileDto);

	return modelMapper.map(updatedProfile, ProfileResponse.class);
    }

    @PostMapping("/initial")
    public ResponseEntity<String> initialSeed(@RequestBody List<ProfileRequest> profileRequests)
	    throws RestApiProfilesException {

	List<ProfileDto> profilesDto = new ArrayList<>();
	for (ProfileRequest profileRequest : profileRequests) {
	    ProfileDto profileDto = modelMapper.map(profileRequest, ProfileDto.class);
	    profilesDto.add(profileDto);
	}

	profileService.initialSeed(profilesDto);

	return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }

    // TODO add delete mapping
}
