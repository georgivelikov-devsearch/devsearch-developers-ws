package devsearch.developers.ws.ui.model.response;

import devsearch.developers.ws.ui.model.request.SkillRequest;

public class SkillDescriptionResponse {

    private String skillDescriptionId;

    private String description;

    private SkillRequest skill;

    private int position;

    private String publicKey;

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

    public int getPosition() {
	return position;
    }

    public void setPosition(int position) {
	this.position = position;
    }

    public String getPublicKey() {
	return publicKey;
    }

    public void setPublicKey(String publicKey) {
	this.publicKey = publicKey;
    }
}
