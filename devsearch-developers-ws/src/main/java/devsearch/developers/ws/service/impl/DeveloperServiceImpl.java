package devsearch.developers.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import devsearch.common.utils.Utils;
import devsearch.developers.ws.exception.ExceptionMessages;
import devsearch.developers.ws.exception.RestApiDevelopersException;
import devsearch.developers.ws.io.entity.DeveloperEntity;
import devsearch.developers.ws.io.repository.DeveloperRepository;
import devsearch.developers.ws.service.DeveloperService;
import devsearch.developers.ws.shared.dto.DeveloperDto;
import devsearch.developers.ws.shared.dto.DeveloperListDto;

@Service
public class DeveloperServiceImpl implements DeveloperService {

    @Autowired
    private DeveloperRepository developerRepository;

    @Override
    public DeveloperDto getDeveloperByDeveloperId(String developerId) {
	DeveloperEntity developerEntity = developerRepository.findByDeveloperId(developerId);

	if (developerEntity == null) {
	    return null;
	}

	return Utils.map(developerEntity, DeveloperDto.class);
    }

    @Override
    public DeveloperDto getDeveloperByUsername(String username) {
	DeveloperEntity developerEntity = developerRepository.findByUsername(username);

	if (developerEntity == null) {
	    return null;
	}

	return Utils.map(developerEntity, DeveloperDto.class);
    }

    @Override
    public DeveloperDto createDeveloper(DeveloperDto developerDto) throws RestApiDevelopersException {
	String username = developerDto.getUsername();

	DeveloperEntity developerEntity = developerRepository.findByUsername(username);
	if (developerEntity != null) {
	    throw new RestApiDevelopersException(ExceptionMessages.PROFILE_ALREADY_EXISTS_FOR_THIS_USER);
	}

	developerDto.setDeveloperId(Utils.generatePublicId());
	developerDto.setUsername(username);

	developerEntity = Utils.map(developerDto, DeveloperEntity.class);

	DeveloperEntity storedDeveloperEntity = null;
	try {
	    storedDeveloperEntity = developerRepository.save(developerEntity);
	} catch (Exception ex) {
	    throw new RestApiDevelopersException(ExceptionMessages.CREATE_RECORD_FAILED, ex.getMessage());
	}

	return Utils.map(storedDeveloperEntity, DeveloperDto.class);
    }

    @Override
    public DeveloperDto updateDeveloper(DeveloperDto developerDto) throws RestApiDevelopersException {
	DeveloperEntity developerEntity = developerRepository.findByUsername(developerDto.getUsername());
	if (developerEntity == null) {
	    throw new RestApiDevelopersException(ExceptionMessages.NO_RECORD_FOUND_WITH_THIS_ID);
	}

	developerEntity.setFirstName(developerDto.getFirstName());
	developerEntity.setLastName(developerDto.getLastName());
	developerEntity.setShortIntro(developerDto.getShortIntro());
	developerEntity.setAbout(developerDto.getAbout());
	// developerEntity.setContactEmail(developerDto.getContactEmail());
	developerEntity.setSocialLinkedIn(developerDto.getSocialLinkedIn());
	developerEntity.setSocialTwitter(developerDto.getSocialTwitter());
	developerEntity.setSocialGithub(developerDto.getSocialGithub());
	developerEntity.setSocialYoutube(developerDto.getSocialYoutube());
	developerEntity.setSocialWebsite(developerDto.getSocialWebsite());
	developerEntity.setLocationCity(developerDto.getLocationCity());
	developerEntity.setLocationCountry(developerDto.getLocationCountry());
	if (developerDto.isNewDeveloperPictureUpload()) {
	    developerEntity.setDeveloperPictureUrl(developerDto.getDeveloperPictureUrl());
	}

	DeveloperEntity updatedDeveloperEntity = null;
	try {
	    updatedDeveloperEntity = developerRepository.save(developerEntity);
	} catch (Exception ex) {
	    System.out.println(ex.getMessage());
	    throw new RestApiDevelopersException(ExceptionMessages.UPDATE_RECORD_FAILED, ex.getMessage());
	}

	return Utils.map(updatedDeveloperEntity, DeveloperDto.class);
    }

    @Override
    public void deleteDeveloper(String developerId) throws RestApiDevelopersException {
	// Check if underlying user is deleted?
	DeveloperEntity developerEntity = developerRepository.findByDeveloperId(developerId);
	if (developerEntity == null) {
	    throw new RestApiDevelopersException(ExceptionMessages.NO_RECORD_FOUND_WITH_THIS_ID);
	}

	try {
	    developerRepository.delete(developerEntity);
	} catch (Exception ex) {
	    throw new RestApiDevelopersException(ExceptionMessages.DELETE_RECORD_FAILED, ex.getMessage());
	}

    }

    @Override
    public DeveloperListDto getDevelopers(int page, int limit, String searchText) throws RestApiDevelopersException {
	DeveloperListDto returnValue = new DeveloperListDto();
	Pageable pageableRequest = PageRequest.of(page, limit);
	Page<DeveloperEntity> developerListPage = null;

	// TODO refactor
	if (searchText != null && !searchText.equals("")) {
	    developerListPage = developerRepository.findAllByText(pageableRequest, searchText);
	} else {
	    developerListPage = developerRepository.findAll(pageableRequest);
	}

	List<DeveloperEntity> developers = developerListPage.getContent();
	int totalPages = developerListPage.getTotalPages();

	List<DeveloperDto> developerDtoList = new ArrayList<>();
	for (DeveloperEntity developerEntity : developers) {
	    DeveloperDto developerDto = Utils.map(developerEntity, DeveloperDto.class);
	    developerDtoList.add(developerDto);
	}

	returnValue.setTotalPages(totalPages);
	returnValue.setDevelopers(developerDtoList);

	return returnValue;
    }

    @Override
    public void initialSeed(List<DeveloperDto> developersDto) throws RestApiDevelopersException {
	List<DeveloperEntity> developers = new ArrayList<>();
	for (DeveloperDto developerDto : developersDto) {
	    DeveloperEntity developerEntity = Utils.map(developerDto, DeveloperEntity.class);
	    developers.add(developerEntity);
	}

	developerRepository.saveAll(developers);
    }

}
