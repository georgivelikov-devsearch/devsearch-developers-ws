package devsearch.developers.ws.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import devsearch.common.utils.Utils;
import devsearch.developers.ws.exception.ExceptionMessages;
import devsearch.developers.ws.exception.RestApiDevelopersException;
import devsearch.developers.ws.io.entity.DeveloperEntity;
import devsearch.developers.ws.io.entity.SkillDescriptionEntity;
import devsearch.developers.ws.io.entity.SkillEntity;
import devsearch.developers.ws.io.repository.DeveloperRepository;
import devsearch.developers.ws.io.repository.SkillDescriptionRepository;
import devsearch.developers.ws.io.repository.SkillRepository;
import devsearch.developers.ws.service.SkillDescriptionService;
import devsearch.developers.ws.shared.dto.SkillDescriptionDto;
import devsearch.developers.ws.shared.mapper.ModelMapper;

@Service
public class SkillDescriptionServiceImpl implements SkillDescriptionService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private SkillDescriptionRepository skillDescriptionRepository;

    @Autowired
    private DeveloperRepository developerRepository;

    @Override
    public SkillDescriptionDto getSkillDescriptionBySkillDescriptionId(String skillDescriptionId) {
	SkillDescriptionEntity skillDescriptionEntity = skillDescriptionRepository
		.findBySkillDescriptionId(skillDescriptionId);

	return mapper.map(skillDescriptionEntity, SkillDescriptionDto.class);
    }

    @Override
    public SkillDescriptionDto createSkillDescription(SkillDescriptionDto skillDescriptionDto)
	    throws RestApiDevelopersException {
	DeveloperEntity developerEntity = developerRepository.findByDeveloperId(skillDescriptionDto.getDeveloperId());
	String skillName = skillDescriptionDto.getSkill().getSkillName();
	Optional<SkillDescriptionEntity> result = developerEntity.getSkillDescriptions()
		.stream()
		.filter(s -> s.getSkill().getSkillName().equals(skillName))
		.findFirst();

	if (!result.isEmpty()) {
	    throw new RestApiDevelopersException("Skill with that name already exists!");
	}

	SkillDescriptionEntity skillDescriptionEntity = mapper.map(skillDescriptionDto, SkillDescriptionEntity.class);

	skillDescriptionEntity.setSkillDescriptionId(Utils.generatePublicId());

	SkillEntity skillEntity = skillRepository.findBySkillId(skillDescriptionDto.getSkill().getSkillId());
	skillDescriptionEntity.setSkill(skillEntity);

	// Starting from 0, the 'order' of the skill is equal to the size of the list
	int position = developerEntity.getSkillDescriptions().size();
	skillDescriptionEntity.setPosition(position);
	skillDescriptionEntity.setDeveloper(developerEntity);

	SkillDescriptionEntity storedEntity = skillDescriptionRepository.save(skillDescriptionEntity);

	return mapper.map(storedEntity, SkillDescriptionDto.class);
    }

    @Override
    public SkillDescriptionDto updateSkillDescription(SkillDescriptionDto skillDescriptionDto)
	    throws RestApiDevelopersException {
	SkillDescriptionEntity skillDescriptionEntity = skillDescriptionRepository
		.findBySkillDescriptionId(skillDescriptionDto.getSkillDescriptionId());

	skillDescriptionEntity.setDescription(skillDescriptionDto.getDescription());

	SkillDescriptionEntity storedEntity = skillDescriptionRepository.save(skillDescriptionEntity);

	return mapper.map(storedEntity, SkillDescriptionDto.class);
    }

    @Override
    public void deleteSkillDescription(String username, String skillDescriptionId) throws RestApiDevelopersException {
	SkillDescriptionEntity skillDescriptionEntity = skillDescriptionRepository
		.findBySkillDescriptionId(skillDescriptionId);
	if (skillDescriptionEntity == null) {
	    throw new RestApiDevelopersException(ExceptionMessages.NO_RECORD_FOUND_WITH_THIS_ID);
	}

	try {
	    skillDescriptionRepository.delete(skillDescriptionEntity);
	} catch (Exception ex) {
	    throw new RestApiDevelopersException(ExceptionMessages.DELETE_RECORD_FAILED, ex.getMessage());
	}

	DeveloperEntity developerEntity = developerRepository.findByUsername(username);
	// re-order existing skill descriptions, smaller position means higher in order
	developerEntity.getSkillDescriptions()
		.sort((SkillDescriptionEntity s1, SkillDescriptionEntity s2) -> s1.getPosition() - s2.getPosition());
	for (int i = 0; i < developerEntity.getSkillDescriptions().size(); i++) {
	    SkillDescriptionEntity currentEntity = developerEntity.getSkillDescriptions().get(i);
	    currentEntity.setPosition(i);
	    skillDescriptionRepository.save(currentEntity);
	}
    }

    @Override
    public List<SkillDescriptionDto> updateSkillDescriptionOrder(String username, List<SkillDescriptionDto> tags)
	    throws RestApiDevelopersException {
	DeveloperEntity developerEntity = developerRepository.findByUsername(username);
	List<SkillDescriptionDto> finalReorderedList = new ArrayList<>();
	for (int i = 0; i < tags.size(); i++) {
	    String skillName = tags.get(i).getSkill().getSkillName();
	    Optional<SkillDescriptionEntity> result = developerEntity.getSkillDescriptions()
		    .stream()
		    .filter(s -> s.getSkill().getSkillName().equals(skillName))
		    .findFirst();
	    SkillDescriptionEntity skillDescriptionEntity = result.get();
	    skillDescriptionEntity.setPosition(i);
	    SkillDescriptionEntity updatedSkillDescriptionEntity = skillDescriptionRepository
		    .save(skillDescriptionEntity);
	    SkillDescriptionDto updatedSkillDescriptionDto = mapper.map(updatedSkillDescriptionEntity,
		    SkillDescriptionDto.class);
	    finalReorderedList.add(updatedSkillDescriptionDto);
	}

	return finalReorderedList;
    }
}
