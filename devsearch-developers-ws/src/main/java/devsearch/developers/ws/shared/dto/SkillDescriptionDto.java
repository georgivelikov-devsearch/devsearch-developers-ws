package devsearch.developers.ws.shared.dto;

public class SkillDescriptionDto {

    private String skillDescriptionId;

    private String description;

    private SkillDto skill;

    private String developerId;

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

    public SkillDto getSkill() {
	return skill;
    }

    public void setSkill(SkillDto skill) {
	this.skill = skill;
    }

    public String getDeveloperId() {
	return developerId;
    }

    public void setDeveloperId(String developerId) {
	this.developerId = developerId;
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
