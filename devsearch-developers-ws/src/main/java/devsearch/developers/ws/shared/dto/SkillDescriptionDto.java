package devsearch.developers.ws.shared.dto;

public class SkillDescriptionDto {

    private String skillDescriptionId;

    private String description;

    private SkillDto skill;

    public String getSkillDescriptionId() {
	return skillDescriptionId;
    }

    public void setSkillDescriptionId(String skillDescriptionId) {
	this.skillDescriptionId = skillDescriptionId;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public SkillDto getSkill() {
	return skill;
    }

    public void setSkill(SkillDto skill) {
	this.skill = skill;
    }
}
