package devsearch.developers.ws.service.impl;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import devsearch.common.utils.Utils;
import devsearch.developers.ws.exception.RestApiDevelopersException;
import devsearch.developers.ws.io.entity.SkillEntity;
import devsearch.developers.ws.io.repository.SkillRepository;
import devsearch.developers.ws.service.SkillService;
import devsearch.developers.ws.shared.dto.SkillDto;
import devsearch.developers.ws.shared.mapper.ModelMapper;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private SkillRepository skillRepository;

    @Override
    public SkillDto getSkillBySkillId(String skillId) {
	SkillEntity skillEntity = skillRepository.findBySkillId(skillId);

	return mapper.map(skillEntity, SkillDto.class);
    }

    @Override
    public SkillDto getSkillBySkillName(String skillName) {
	SkillEntity skillEntity = skillRepository.findBySkillName(skillName);

	if (skillEntity == null) {
	    return null;
	}

	return mapper.map(skillEntity, SkillDto.class);
    }

    @Override
    public SkillDto createSkill(SkillDto skillDto) throws RestApiDevelopersException {
	String skillName = skillDto.getSkillName();

	SkillEntity skillEntity = skillRepository.findBySkillName(skillName);
	if (skillEntity == null) {
	    skillEntity = new SkillEntity();
	    skillEntity.setSkillId(Utils.generatePublicId());
	    skillEntity.setSkillName(skillName);
	    skillEntity.setSkillDescriptions(new HashSet<>());
	    skillRepository.save(skillEntity);
	}

	return mapper.map(skillEntity, SkillDto.class);
    }

}
