package devsearch.developers.ws.ui.model.request;

public class SkillDescriptionRequest {

    private String skillDescriptionId;

    private String description;

    private SkillRequest skill;

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

    public SkillRequest getSkill() {
	return skill;
    }

    public void setSkill(SkillRequest skill) {
	this.skill = skill;
    }
}