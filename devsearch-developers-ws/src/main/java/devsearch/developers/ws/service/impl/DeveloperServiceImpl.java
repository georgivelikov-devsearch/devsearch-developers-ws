package devsearch.developers.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import devsearch.common.exception.DevsearchApiException;
import devsearch.common.utils.Utils;
import devsearch.developers.ws.exception.ExceptionMessages;
import devsearch.developers.ws.io.entity.DeveloperEntity;
import devsearch.developers.ws.io.repository.DeveloperRepository;
import devsearch.developers.ws.service.DeveloperService;
import devsearch.developers.ws.shared.dto.DeveloperDto;
import devsearch.developers.ws.shared.dto.DeveloperListDto;
import devsearch.developers.ws.shared.mapper.ModelMapper;

@Service
public class DeveloperServiceImpl implements DeveloperService {

    @Autowired
    private DeveloperRepository developerRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public DeveloperDto getDeveloperByDeveloperId(String developerId) {
	DeveloperEntity developerEntity = developerRepository.findByDeveloperId(developerId);

	if (developerEntity == null) {
	    return null;
	}

	return mapper.map(developerEntity, DeveloperDto.class);
    }

    @Override
    public DeveloperDto getDeveloperByUsername(String username) {
	DeveloperEntity developerEntity = developerRepository.findByUsername(username);

	if (developerEntity == null) {
	    return null;
	}

	return mapper.map(developerEntity, DeveloperDto.class);
    }

    @Override
    public DeveloperDto createDeveloper(DeveloperDto developerDto) throws DevsearchApiException {
	String username = developerDto.getUsername();

	DeveloperEntity developerEntity = developerRepository.findByUsername(username);
	if (developerEntity != null) {
	    throw new DevsearchApiException(ExceptionMessages.PROFILE_ALREADY_EXISTS_FOR_THIS_USER.toString());
	}

	developerDto.setDeveloperId(Utils.generateId());
	developerDto.setUsername(username);

	developerEntity = mapper.map(developerDto, DeveloperEntity.class);

	DeveloperEntity storedDeveloperEntity = null;
	try {
	    storedDeveloperEntity = developerRepository.save(developerEntity);
	} catch (Exception ex) {
	    throw new DevsearchApiException(ExceptionMessages.CREATE_RECORD_FAILED.toString(), ex.getMessage());
	}

	return mapper.map(storedDeveloperEntity, DeveloperDto.class);
    }

    @Override
    public DeveloperDto updateDeveloper(DeveloperDto developerDto) throws DevsearchApiException {
	DeveloperEntity developerEntity = developerRepository.findByUsername(developerDto.getUsername());
	if (developerEntity == null) {
	    throw new DevsearchApiException(ExceptionMessages.NO_RECORD_FOUND_WITH_THIS_ID.toString());
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
	    throw new DevsearchApiException(ExceptionMessages.UPDATE_RECORD_FAILED.toString(), ex.getMessage());
	}

	return mapper.map(updatedDeveloperEntity, DeveloperDto.class);
    }

    @Override
    public void deleteDeveloper(String developerId) throws DevsearchApiException {
	// Check if underlying user is deleted?
	DeveloperEntity developerEntity = developerRepository.findByDeveloperId(developerId);
	if (developerEntity == null) {
	    throw new DevsearchApiException(ExceptionMessages.NO_RECORD_FOUND_WITH_THIS_ID.toString());
	}

	try {
	    developerRepository.delete(developerEntity);
	} catch (Exception ex) {
	    throw new DevsearchApiException(ExceptionMessages.DELETE_RECORD_FAILED.toString(), ex.getMessage());
	}

    }

    @Override
    public DeveloperListDto getDevelopers(int page, int limit, String searchText) throws DevsearchApiException {
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
	    DeveloperDto developerDto = mapper.map(developerEntity, DeveloperDto.class);
	    developerDtoList.add(developerDto);
	}

	returnValue.setTotalPages(totalPages);
	returnValue.setDevelopers(developerDtoList);

	return returnValue;
    }

    @Override
    public void initialSeed(List<DeveloperDto> developersDto) throws DevsearchApiException {
	List<DeveloperEntity> developers = new ArrayList<>();
	for (DeveloperDto developerDto : developersDto) {
	    DeveloperEntity developerEntity = mapper.map(developerDto, DeveloperEntity.class);
	    developers.add(developerEntity);
	}

	developerRepository.saveAll(developers);
    }

}
