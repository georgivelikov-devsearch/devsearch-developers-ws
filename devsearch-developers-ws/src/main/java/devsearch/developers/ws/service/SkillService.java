package devsearch.developers.ws.service;

import devsearch.developers.ws.exception.RestApiDevelopersException;
import devsearch.developers.ws.shared.dto.SkillDto;

public interface SkillService {

    public SkillDto getSkillBySkillId(String skillId);

    public SkillDto getSkillBySkillName(String skillName);

    public SkillDto createSkill(SkillDto skill) throws RestApiDevelopersException;

}
