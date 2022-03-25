package devsearch.developers.ws.ui.model.request;

public class ProfileImageRequest {

    private String profilePictureBase64;
    private String profileId;

    public String getProfilePictureBase64() {
	return profilePictureBase64;
    }

    public void setProfilePictureBase64(String profilePictureBase64) {
	this.profilePictureBase64 = profilePictureBase64;
    }

    public String getProfileId() {
	return profileId;
    }

    public void setProfileId(String profileId) {
	this.profileId = profileId;
    }
}
