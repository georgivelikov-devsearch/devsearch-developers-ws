package devsearch.developers.ws.service;

import devsearch.developers.ws.exception.RestApiDevelopersException;
import devsearch.developers.ws.shared.dto.SkillDescriptionDto;

public interface SkillDescriptionService {

    public SkillDescriptionDto getSkillDescriptionBySkillDescriptionId(String skillDescriptionId);

    public SkillDescriptionDto createSkillDescription(SkillDescriptionDto skillDescriptionDto)
	    throws RestApiDevelopersException;

    public SkillDescriptionDto updateSkillDescription(SkillDescriptionDto skillDescriptionDto)
	    throws RestApiDevelopersException;

    public void deleteSkillDescription(String skillDescriptionId) throws RestApiDevelopersException;
}
