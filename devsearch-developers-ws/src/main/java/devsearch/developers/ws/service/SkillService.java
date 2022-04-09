package devsearch.developers.ws.service;

import devsearch.common.exception.DevsearchApiException;
import devsearch.developers.ws.shared.dto.SkillDto;

public interface SkillService {

    public SkillDto getSkillBySkillId(String skillId);

    public SkillDto getSkillBySkillName(String skillName);

    public SkillDto createSkill(SkillDto skillDto) throws DevsearchApiException;

}
