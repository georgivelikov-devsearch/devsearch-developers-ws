package devsearch.developers.ws.service.impl;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import devsearch.developers.ws.exception.RestApiDevelopersException;
import devsearch.developers.ws.io.entity.SkillEntity;
import devsearch.developers.ws.io.repository.SkillRepository;
import devsearch.developers.ws.service.SkillService;
import devsearch.developers.ws.shared.dto.SkillDto;
import devsearch.developers.ws.shared.utils.AppConstants;
import devsearch.developers.ws.shared.utils.Mapper;
import devsearch.developers.ws.shared.utils.Utils;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private Mapper modelMapper;

    @Autowired
    private Utils utils;

    @Override
    public SkillDto getSkillBySkillId(String skillId) {
	SkillEntity skillEntity = skillRepository.findBySkillId(skillId);

	return modelMapper.map(skillEntity, SkillDto.class);
    }

    @Override
    public SkillDto getSkillBySkillName(String skillName) {
	SkillEntity skillEntity = skillRepository.findBySkillName(skillName);

	return modelMapper.map(skillEntity, SkillDto.class);
    }

    @Override
    public SkillDto createSkill(SkillDto skillDto) throws RestApiDevelopersException {
	String skillName = skillDto.getSkillName();

	SkillEntity skillEntity = skillRepository.findBySkillName(skillName);
	if (skillEntity == null) {
	    skillEntity = new SkillEntity();
	    skillEntity.setSkillId(utils.generatePublicId(AppConstants.PRIVATE_ID_LENGTH));
	    skillEntity.setSkillName(skillName);
	    skillEntity.setSkillDescriptions(new HashSet<>());
	    skillRepository.save(skillEntity);
	}

	return modelMapper.map(skillEntity, SkillDto.class);
    }

}
