package devsearch.profiles.ws.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "profiles")
public class ProfileEntity implements Serializable {

    private static final long serialVersionUID = -4809528528475089777L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true)
    private String profileId;

    @Column(nullable = false, unique = true)
    @Size(min = 1, max = 50)
    private String username;

    @Column(nullable = false)
    @Size(min = 1, max = 50)
    private String firstName;

    @Column(nullable = false)
    @Size(min = 1, max = 50)
    private String lastName;

    @Column(nullable = true, length = 50)
    private String contactEmail;

    @Column(nullable = true, length = 200)
    private String shortIntro;

    @Column(nullable = true, length = 3000)
    private String about;

    @Column(nullable = true, length = 200)
    private String socialLinkedIn;

    @Column(nullable = true, length = 200)
    private String socialTwitter;

    @Column(nullable = true, length = 200)
    private String socialGithub;

    @Column(nullable = true, length = 200)
    private String socialYoutube;

    @Column(nullable = true, length = 200)
    private String socialWebsite;

    @Column(nullable = true, length = 50)
    private String locationCity;

    @Column(nullable = true, length = 50)
    private String locationCountry;

    @Column(nullable = true, length = 200)
    private String profilePictureUrl;

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public String getProfileId() {
	return profileId;
    }

    public void setProfileId(String profileId) {
	this.profileId = profileId;
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

    public String getProfilePictureUrl() {
	return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
	this.profilePictureUrl = profilePictureUrl;
    }
}