package devsearch.developers.ws.ui.model.request;

public class DeveloperRequest {

    private String developerId;
    private String username;
    private String firstName;
    private String lastName;
    private String contactEmail;
    private String shortIntro;
    private String about;
    private String socialLinkedIn;
    private String socialTwitter;
    private String socialGithub;
    private String socialYoutube;
    private String socialWebsite;
    private String locationCity;
    private String locationCountry;
    private String developerPictureBase64;
    private boolean newDeveloperPictureUpload;

    public String getDeveloperId() {
	return developerId;
    }

    public void setDeveloperId(String developerId) {
	this.developerId = developerId;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public String getContactEmail() {
	return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
	this.contactEmail = contactEmail;
    }

    public String getShortIntro() {
	return shortIntro;
    }

    public void setShortIntro(String shortIntro) {
	this.shortIntro = shortIntro;
    }

    public String getAbout() {
	return about;
    }

    public void setAbout(String about) {
	this.about = about;
    }

    public String getSocialLinkedIn() {
	return socialLinkedIn;
    }

    public void setSocialLinkedIn(String socialLinkedIn) {
	this.socialLinkedIn = socialLinkedIn;
    }

    public String getSocialTwitter() {
	return socialTwitter;
    }

    public void setSocialTwitter(String socialTwitter) {
	this.socialTwitter = socialTwitter;
    }

    public String getSocialGithub() {
	return socialGithub;
    }

    public void setSocialGithub(String socialGithub) {
	this.socialGithub = socialGithub;
    }

    public String getSocialYoutube() {
	return socialYoutube;
    }

    public void setSocialYoutube(String socialYoutube) {
	this.socialYoutube = socialYoutube;
    }

    public String getSocialWebsite() {
	return socialWebsite;
    }

    public void setSocialWebsite(String socialWebsite) {
	this.socialWebsite = socialWebsite;
    }

    public String getLocationCity() {
	return locationCity;
    }

    public void setLocationCity(String locationCity) {
	this.locationCity = locationCity;
    }

    public String getLocationCountry() {
	return locationCountry;
    }

    public void setLocationCountry(String locationCountry) {
	this.locationCountry = locationCountry;
    }

    public String getDeveloperPictureBase64() {
	return developerPictureBase64;
    }

    public void setDeveloperPictureBase64(String developerPictureBase64) {
	this.developerPictureBase64 = developerPictureBase64;
    }

    public boolean getNewDeveloperPictureUpload() {
	return newDeveloperPictureUpload;
    }

    public void setNewDeveloperPictureUpload(boolean newDeveloperPictureUpload) {
	this.newDeveloperPictureUpload = newDeveloperPictureUpload;
    }
}
