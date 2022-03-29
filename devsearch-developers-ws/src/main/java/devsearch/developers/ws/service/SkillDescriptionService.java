package devsearch.developers.ws.service;

import devsearch.developers.ws.exception.RestApiDevelopersException;
import devsearch.developers.ws.shared.dto.SkillDescriptionDto;

public interface SkillDescriptionService {

    public SkillDescriptionDto getSkillDescriptionBySkillDescriptionId(String skillDescriptionId);

    public SkillDescriptionDto createSkillDescriptionDto(SkillDescriptionDto skillDescriptionDto)
	    throws RestApiDevelopersException;

    public SkillDescriptionDto updateSkillDescriptionDto(SkillDescriptionDto skillDescriptionDto)
	    throws RestApiDevelopersException;
}
