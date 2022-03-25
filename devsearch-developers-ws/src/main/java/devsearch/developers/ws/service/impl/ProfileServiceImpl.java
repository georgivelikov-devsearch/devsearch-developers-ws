package devsearch.developers.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import devsearch.developers.ws.exception.ExceptionMessages;
import devsearch.developers.ws.exception.RestApiProfilesException;
import devsearch.developers.ws.io.entity.ProfileEntity;
import devsearch.developers.ws.io.repository.ProfileRepository;
import devsearch.developers.ws.service.ProfileService;
import devsearch.developers.ws.shared.dto.ProfileDto;
import devsearch.developers.ws.shared.dto.ProfileListDto;
import devsearch.developers.ws.shared.utils.AppConstants;
import devsearch.developers.ws.shared.utils.Mapper;
import devsearch.developers.ws.shared.utils.Utils;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private Mapper modelMapper;

    @Autowired
    private Utils utils;

    @Override
    public ProfileDto getProfileByProfileId(String profileId) throws RestApiProfilesException {
	ProfileEntity profileEntity = profileRepository.findByProfileId(profileId);

	if (profileEntity == null) {
	    throw new RestApiProfilesException(ExceptionMessages.NO_RECORD_FOUND_WITH_THIS_ID);
	}

	return modelMapper.map(profileEntity, ProfileDto.class);
    }

    @Override
    public ProfileDto getProfileByUsername(String username) throws RestApiProfilesException {
	ProfileEntity profileEntity = profileRepository.findByUsername(username);
	if (profileEntity == null) {
	    throw new RestApiProfilesException(ExceptionMessages.NO_PFOFILE_FOUND_FOR_THIS_USER);
	}

	return modelMapper.map(profileEntity, ProfileDto.class);
    }

    @Override
    public ProfileDto createProfile(ProfileDto profileDto) throws RestApiProfilesException {
	String username = profileDto.getUsername();

	ProfileEntity profileEntity = profileRepository.findByUsername(username);
	if (profileEntity != null) {
	    throw new RestApiProfilesException(ExceptionMessages.PROFILE_ALREADY_EXISTS_FOR_THIS_USER);
	}

	profileDto.setProfileId(utils.generatePublicId(AppConstants.PRIVATE_ID_LENGTH));
	profileDto.setUsername(username);

	profileEntity = modelMapper.map(profileDto, ProfileEntity.class);

	ProfileEntity storedProfileEntity = null;
	try {
	    storedProfileEntity = profileRepository.save(profileEntity);
	} catch (Exception ex) {
	    throw new RestApiProfilesException(ExceptionMessages.CREATE_RECORD_FAILED, ex.getMessage());
	}

	return modelMapper.map(storedProfileEntity, ProfileDto.class);
    }

    @Override
    public ProfileDto updateProfile(ProfileDto profileDto) throws RestApiProfilesException {
	ProfileEntity profileEntity = profileRepository.findByUsername(profileDto.getUsername());
	if (profileEntity == null) {
	    throw new RestApiProfilesException(ExceptionMessages.NO_RECORD_FOUND_WITH_THIS_ID);
	}

	profileEntity.setFirstName(profileDto.getFirstName());
	profileEntity.setLastName(profileDto.getLastName());
	profileEntity.setShortIntro(profileDto.getShortIntro());
	profileEntity.setAbout(profileDto.getAbout());
	profileEntity.setContactEmail(profileEntity.getContactEmail());
	profileEntity.setSocialLinkedIn(profileDto.getSocialLinkedIn());
	profileEntity.setSocialTwitter(profileDto.getSocialTwitter());
	profileEntity.setSocialGithub(profileDto.getSocialGithub());
	profileEntity.setSocialYoutube(profileDto.getSocialYoutube());
	profileEntity.setSocialWebsite(profileDto.getSocialWebsite());
	profileEntity.setLocationCity(profileDto.getLocationCity());
	profileEntity.setLocationCountry(profileDto.getLocationCountry());
	if (profileDto.isNewProfilePictureUpload()) {
	    profileEntity.setProfilePictureUrl(profileDto.getProfilePictureUrl());
	}

	ProfileEntity updatedProfileEntity = null;
	try {
	    updatedProfileEntity = profileRepository.save(profileEntity);
	} catch (Exception ex) {
	    System.out.println(ex.getMessage());
	    throw new RestApiProfilesException(ExceptionMessages.UPDATE_RECORD_FAILED, ex.getMessage());
	}

	return modelMapper.map(updatedProfileEntity, ProfileDto.class);
    }

    @Override
    public void deleteProfile(String profileId) throws RestApiProfilesException {
	// Check if underlying user is deleted?
	ProfileEntity profileEntity = profileRepository.findByProfileId(profileId);
	if (profileEntity == null) {
	    throw new RestApiProfilesException(ExceptionMessages.NO_RECORD_FOUND_WITH_THIS_ID);
	}

	try {
	    profileRepository.delete(profileEntity);
	} catch (Exception ex) {
	    throw new RestApiProfilesException(ExceptionMessages.DELETE_RECORD_FAILED, ex.getMessage());
	}

    }

    @Override
    public ProfileListDto getProfiles(int page, int limit, String searchText) throws RestApiProfilesException {
	ProfileListDto returnValue = new ProfileListDto();
	Pageable pageableRequest = PageRequest.of(page, limit);
	Page<ProfileEntity> profileListPage = null;

	// TODO refactor
	if (searchText != null && !searchText.equals("")) {
	    profileListPage = profileRepository.findAllByText(pageableRequest, searchText);
	} else {
	    profileListPage = profileRepository.findAll(pageableRequest);
	}

	List<ProfileEntity> profiles = profileListPage.getContent();
	int totalPages = profileListPage.getTotalPages();

	List<ProfileDto> profileDtoList = new ArrayList<>();
	for (ProfileEntity profileEntity : profiles) {
	    ProfileDto profileDto = modelMapper.map(profileEntity, ProfileDto.class);
	    profileDtoList.add(profileDto);
	}

	returnValue.setTotalPages(totalPages);
	returnValue.setProfiles(profileDtoList);

	return returnValue;
    }

    @Override
    public void initialSeed(List<ProfileDto> profilesDto) throws RestApiProfilesException {
	List<ProfileEntity> profiles = new ArrayList<>();
	for (ProfileDto profileDto : profilesDto) {
	    ProfileEntity profileEntity = modelMapper.map(profileDto, ProfileEntity.class);
	    profiles.add(profileEntity);
	}

	profileRepository.saveAll(profiles);
    }

}
