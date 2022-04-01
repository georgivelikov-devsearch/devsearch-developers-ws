package devsearch.developers.ws.ui.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import devsearch.developers.ws.exception.RestApiDevelopersException;
import devsearch.developers.ws.service.SkillDescriptionService;
import devsearch.developers.ws.service.SkillService;
import devsearch.developers.ws.shared.dto.SkillDescriptionDto;
import devsearch.developers.ws.shared.dto.SkillDto;
import devsearch.developers.ws.shared.utils.Mapper;
import devsearch.developers.ws.ui.model.request.SkillDescriptionRequest;
import devsearch.developers.ws.ui.model.response.SkillDescriptionResponse;

@RestController
@RequestMapping("skills")
public class SkillDescriptionController {

    @Autowired
    private SkillService skillService;

    @Autowired
    private SkillDescriptionService skillDescriptionService;

    @Autowired
    private Mapper modelMapper;

    @PostMapping()
    public SkillDescriptionResponse createSkillDescription(@RequestBody SkillDescriptionRequest skillDescriptionRequest)
	    throws RestApiDevelopersException {
	SkillDescriptionDto skillDescriptionDto = modelMapper.map(skillDescriptionRequest, SkillDescriptionDto.class);

	// Create new skill if it does not exists
	SkillDto skillDto = skillService.getSkillBySkillName(skillDescriptionRequest.getSkill().getSkillName());
	if (skillDto == null) {
	    SkillDto newSkillDto = modelMapper.map(skillDescriptionRequest.getSkill(), SkillDto.class);
	    skillDto = skillService.createSkill(newSkillDto);
	}

	skillDescriptionDto.setSkill(skillDto);

	SkillDescriptionDto newSkillDescriptionDto = skillDescriptionService
		.createSkillDescription(skillDescriptionDto);
	return modelMapper.map(newSkillDescriptionDto, SkillDescriptionResponse.class);
    }

    @PutMapping()
    public SkillDescriptionResponse updateSkillDescription(@RequestBody SkillDescriptionRequest skillDescriptionRequest)
	    throws RestApiDevelopersException {
	SkillDescriptionDto skillDescriptionDto = modelMapper.map(skillDescriptionRequest, SkillDescriptionDto.class);

	// Create new skill if it does not exists
	SkillDto skillDto = skillService.getSkillBySkillName(skillDescriptionRequest.getSkill().getSkillName());
	if (skillDto == null) {
	    throw new RestApiDevelopersException("Skill does not exist");
	}

	SkillDescriptionDto editedSkillDescriptionDto = skillDescriptionService
		.updateSkillDescription(skillDescriptionDto);
	return modelMapper.map(editedSkillDescriptionDto, SkillDescriptionResponse.class);
    }

    @DeleteMapping(path = "/{skillDescriptionId}")
    public ResponseEntity<String> deleteSkillDescription(@PathVariable String skillDescriptionId)
	    throws RestApiDevelopersException {
	skillDescriptionService.deleteSkillDescription(skillDescriptionId);

	return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }
}
