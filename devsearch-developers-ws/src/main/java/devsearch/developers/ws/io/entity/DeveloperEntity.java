package devsearch.developers.ws.io.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "developers")
public class DeveloperEntity implements Serializable {

    private static final long serialVersionUID = -4809528528475089777L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true, length = 30)
    private String developerId;

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
    private String developerPictureUrl;

    @OneToMany(mappedBy = "developer")
    private List<SkillDescriptionEntity> skillDescriptions;

    @OneToMany(mappedBy = "developer")
    private List<CommentEntity> comments;

    @Column(nullable = false, unique = true, length = 30)
    private String publicKey;

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

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

    public String getDeveloperPictureUrl() {
	return developerPictureUrl;
    }

    public void setDeveloperPictureUrl(String developerPictureUrl) {
	this.developerPictureUrl = developerPictureUrl;
    }

    public List<SkillDescriptionEntity> getSkillDescriptions() {
	return skillDescriptions;
    }

    public void setSkillDescriptions(List<SkillDescriptionEntity> skillDescriptions) {
	this.skillDescriptions = skillDescriptions;
    }

    public String getPublicKey() {
	return publicKey;
    }

    public void setPublicKey(String publicKey) {
	this.publicKey = publicKey;
    }

    public List<CommentEntity> getComments() {
	return comments;
    }

    public void setComments(List<CommentEntity> comments) {
	this.comments = comments;
    }
}