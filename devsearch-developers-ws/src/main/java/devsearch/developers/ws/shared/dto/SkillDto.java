package devsearch.developers.ws.shared.dto;

import java.util.Set;

public class SkillDto {

    private String skillId;

    private String skillName;

    private Set<SkillDescriptionDto> skillDescriptions;

    public String getSkillId() {
	return skillId;
    }

    public void setSkillId(String skillId) {
	this.skillId = skillId;
    }

    public String getSkillName() {
	return skillName;
    }

    public void setSkillName(String skillName) {
	this.skillName = skillName;
    }

    public Set<SkillDescriptionDto> getSkillDescriptions() {
	return skillDescriptions;
    }

    public void setSkillDescriptions(Set<SkillDescriptionDto> skillDescriptions) {
	this.skillDescriptions = skillDescriptions;
    }
}
