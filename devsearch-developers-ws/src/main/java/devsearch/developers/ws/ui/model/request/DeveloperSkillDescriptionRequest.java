package devsearch.developers.ws.ui.model.request;

public class DeveloperSkillDescriptionRequest {

    private String developerId;

    private SkillDescriptionRequest skillDescription;

    public String getDeveloperId() {
	return developerId;
    }

    public void setDeveloperId(String developerId) {
	this.developerId = developerId;
    }

    public SkillDescriptionRequest getSkillDescription() {
	return skillDescription;
    }

    public void setSkillDescription(SkillDescriptionRequest skillDescription) {
	this.skillDescription = skillDescription;
    }
}
