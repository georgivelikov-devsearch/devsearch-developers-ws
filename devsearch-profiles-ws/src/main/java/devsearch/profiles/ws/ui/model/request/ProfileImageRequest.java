package devsearch.profiles.ws.ui.model.request;

public class ProfileImageRequest {

    private String profilePictureBase64;
    private String profilePrivateId;

    public String getProfilePictureBase64() {
	return profilePictureBase64;
    }

    public void setProfilePictureBase64(String profilePictureBase64) {
	this.profilePictureBase64 = profilePictureBase64;
    }

    public String getProfilePrivateId() {
	return profilePrivateId;
    }

    public void setProfilePrivateId(String profilePrivateId) {
	this.profilePrivateId = profilePrivateId;
    }
}
