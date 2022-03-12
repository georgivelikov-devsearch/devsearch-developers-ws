package devsearch.profiles.ws.ui.contorller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import devsearch.profiles.ws.client.ImageClient;
import devsearch.profiles.ws.exception.RestApiProfilesException;
//import devsearch.profiles.ws.service.ImageService;
import devsearch.profiles.ws.service.ProfileService;
import devsearch.profiles.ws.shared.dto.ProfileDto;
import devsearch.profiles.ws.shared.dto.ProfileListDto;
import devsearch.profiles.ws.shared.utils.Mapper;
import devsearch.profiles.ws.ui.model.request.ProfileImageRequest;
import devsearch.profiles.ws.ui.model.request.ProfileRequest;
import devsearch.profiles.ws.ui.model.response.ImageResponse;
import devsearch.profiles.ws.ui.model.response.ProfilePrivateResponse;
import devsearch.profiles.ws.ui.model.response.ProfilePublicListResponse;
import devsearch.profiles.ws.ui.model.response.ProfilePublicResponse;

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

    @GetMapping(path = "/user/{userId}")
    public ProfilePrivateResponse getProfileByUserId(@PathVariable String userId) throws RestApiProfilesException {
	ProfileDto profileDto = profileService.getProfileByUserId(userId);

	return modelMapper.map(profileDto, ProfilePrivateResponse.class);
    }

    @GetMapping(path = "/private/{profilePrivateId}")
    public ProfilePrivateResponse getProfileByPrivateId(@PathVariable String profilePrivateId)
	    throws RestApiProfilesException {
	ProfileDto profileDto = profileService.getProfileByProfilePrivateId(profilePrivateId);

	return modelMapper.map(profileDto, ProfilePrivateResponse.class);
    }

    @GetMapping(path = "/public/{profilePublicId}")
    public ProfilePublicResponse getProfileByPublicId(@PathVariable String profilePublicId)
	    throws RestApiProfilesException {
	ProfileDto profileDto = profileService.getProfileByProfilePublicId(profilePublicId);

	return modelMapper.map(profileDto, ProfilePublicResponse.class);
    }

    @GetMapping(path = "/public")
    public ProfilePublicListResponse getPublicProfiles(@RequestParam(value = "page", defaultValue = "1") int page,
	    @RequestParam(value = "limit", defaultValue = "6") int limit,
	    @RequestParam(value = "userId", defaultValue = "") String userId,
	    @RequestParam(value = "searchText", defaultValue = "") String searchText) throws RestApiProfilesException {

	// In the Repository implementation pagination starts with '0', but in UI
	// usually pages start from 1, 2, 3 etc. So UI will send the number of the page,
	// which should be reduced by 1
	if (page > 0) {
	    page -= 1;
	}

	ProfileListDto profiles = profileService.getPublicProfiles(page, limit, searchText);
	Collection<ProfilePublicResponse> responseProfiles = new ArrayList<ProfilePublicResponse>();
	boolean senderFound = false;
	for (ProfileDto profile : profiles.getProfiles()) {
	    ProfilePublicResponse publicProfile = modelMapper.map(profile, ProfilePublicResponse.class);
	    if (!senderFound && profile.getUserId().equals(userId)) {
		publicProfile.setSender(true);
		senderFound = true;
	    }

	    responseProfiles.add(publicProfile);
	}

	ProfilePublicListResponse response = new ProfilePublicListResponse();
	response.setTotalPages(profiles.getTotalPages());
	response.setProfiles(responseProfiles);

	return response;
    }

    @PostMapping
    public ProfilePrivateResponse createProfile(@RequestBody ProfileRequest profileRequest)
	    throws RestApiProfilesException {
	ProfileDto profileDto = modelMapper.map(profileRequest, ProfileDto.class);
	ProfileDto createdProfile = profileService.createProfile(profileDto);

	return modelMapper.map(createdProfile, ProfilePrivateResponse.class);
    }

    @PutMapping
    public ProfilePrivateResponse updateProfile(@RequestBody ProfileRequest profileRequest)
	    throws RestApiProfilesException {
	ProfileDto profileDto = modelMapper.map(profileRequest, ProfileDto.class);

	if (profileDto.isNewProfilePictureUpload()) {
	    ProfileImageRequest imageRequest = new ProfileImageRequest();
	    imageRequest.setProfilePictureBase64(profileDto.getProfilePictureBase64());
	    imageRequest.setProfilePrivateId(profileDto.getProfilePrivateId());

	    ResponseEntity<ImageResponse> imageResponse = imageClient.addProfileImage(imageRequest);
	    String profilePictureUrl = imageResponse.getBody().getProfilePictureUrl();
	    profileDto.setProfilePictureUrl(profilePictureUrl);
	}

	ProfileDto updatedProfile = profileService.updateProfile(profileDto);

	return modelMapper.map(updatedProfile, ProfilePrivateResponse.class);
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
}
