package devsearch.developers.ws.ui.model.request;

public class DeveloperImageRequest {

    private String developerId;
    private String developerPictureBase64;

    public String getDeveloperId() {
	return developerId;
    }

    public void setDeveloperId(String developerId) {
	this.developerId = developerId;
    }

    public String getDeveloperPictureBase64() {
	return developerPictureBase64;
    }

    public void setDeveloperPictureBase64(String developerPictureBase64) {
	this.developerPictureBase64 = developerPictureBase64;
    }

}
