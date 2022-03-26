package devsearch.developers.ws.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import devsearch.developers.ws.exception.RestApiDevelopersException;
import devsearch.developers.ws.io.entity.SkillDescriptionEntity;
import devsearch.developers.ws.io.repository.SkillDescriptionRepository;
import devsearch.developers.ws.service.SkillDescriptionService;
import devsearch.developers.ws.shared.dto.SkillDescriptionDto;
import devsearch.developers.ws.shared.utils.AppConstants;
import devsearch.developers.ws.shared.utils.Mapper;
import devsearch.developers.ws.shared.utils.Utils;

@Service
public class SkillDescriptionServiceImpl implements SkillDescriptionService {

    @Autowired
    private SkillDescriptionRepository skillDescriptionRepository;

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
    public SkillDescriptionDto createSkillDescriptionDto(SkillDescriptionDto skillDescriptionDto)
	    throws RestApiDevelopersException {
	SkillDescriptionEntity skillDescriptionEntity = modelMapper.map(skillDescriptionDto,
		SkillDescriptionEntity.class);
	skillDescriptionEntity.setSkillDescriptionId(utils.generatePublicId(AppConstants.PRIVATE_ID_LENGTH));

	SkillDescriptionEntity storedEntity = skillDescriptionRepository.save(skillDescriptionEntity);

	return modelMapper.map(storedEntity, SkillDescriptionDto.class);
    }

}
