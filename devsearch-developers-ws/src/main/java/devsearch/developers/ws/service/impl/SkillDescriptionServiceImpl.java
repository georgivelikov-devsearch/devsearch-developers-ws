package devsearch.developers.ws.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import devsearch.developers.ws.exception.RestApiDevelopersException;
import devsearch.developers.ws.io.entity.DeveloperEntity;
import devsearch.developers.ws.io.entity.SkillDescriptionEntity;
import devsearch.developers.ws.io.entity.SkillEntity;
import devsearch.developers.ws.io.repository.DeveloperRepository;
import devsearch.developers.ws.io.repository.SkillDescriptionRepository;
import devsearch.developers.ws.io.repository.SkillRepository;
import devsearch.developers.ws.service.SkillDescriptionService;
import devsearch.developers.ws.shared.dto.SkillDescriptionDto;
import devsearch.developers.ws.shared.utils.AppConstants;
import devsearch.developers.ws.shared.utils.Mapper;
import devsearch.developers.ws.shared.utils.Utils;

@Service
public class SkillDescriptionServiceImpl implements SkillDescriptionService {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private SkillDescriptionRepository skillDescriptionRepository;

    @Autowired
    private DeveloperRepository developerRepository;

    @Autowired
    private Mapper modelMapper;

    @Autowired
    private Utils utils;

    @Override
    public SkillDescriptionDto getSkillDescriptionBySkillDescriptionId(String skillDescriptionId) {
	SkillDescriptionEntity skillDescriptionEntity = skillDescriptionRepository
		.findBySkillDescriptionId(skillDescriptionId);

	return modelMapper.map(skillDescriptionEntity, SkillDescriptionDto.class);
    }

    @Override
    public SkillDescriptionDto createSkillDescription(SkillDescriptionDto skillDescriptionDto)
	    throws RestApiDevelopersException {
	SkillDescriptionEntity skillDescriptionEntity = modelMapper.map(skillDescriptionDto,
		SkillDescriptionEntity.class);

	skillDescriptionEntity.setSkillDescriptionId(utils.generatePublicId(AppConstants.PRIVATE_ID_LENGTH));

	SkillEntity skillEntity = skillRepository.findBySkillId(skillDescriptionDto.getSkill().getSkillId());
	skillDescriptionEntity.setSkill(skillEntity);

	DeveloperEntity developerEntity = developerRepository.findByDeveloperId(skillDescriptionDto.getDeveloperId());
	skillDescriptionEntity.setDeveloper(developerEntity);

	SkillDescriptionEntity storedEntity = skillDescriptionRepository.save(skillDescriptionEntity);

	return modelMapper.map(storedEntity, SkillDescriptionDto.class);
    }

    @Override
    public SkillDescriptionDto updateSkillDescription(SkillDescriptionDto skillDescriptionDto)
	    throws RestApiDevelopersException {
	SkillDescriptionEntity skillDescriptionEntity = skillDescriptionRepository
		.findBySkillDescriptionId(skillDescriptionDto.getSkillDescriptionId());

	skillDescriptionEntity.setDescription(skillDescriptionDto.getDescription());

	SkillDescriptionEntity storedEntity = skillDescriptionRepository.save(skillDescriptionEntity);

	return modelMapper.map(storedEntity, SkillDescriptionDto.class);
    }

    @Override
    public void deleteSkillDescription(String skillDescriptionId) throws RestApiDevelopersException {
	// TODO Auto-generated method stub

    }

}
