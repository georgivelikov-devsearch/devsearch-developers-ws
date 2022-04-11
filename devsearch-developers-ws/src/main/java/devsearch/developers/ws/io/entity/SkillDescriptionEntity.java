package devsearch.developers.ws.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "skill_descriptions")
public class SkillDescriptionEntity implements Serializable {

    private static final long serialVersionUID = -5022442235769061787L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true, length = 30)
    private String skillDescriptionId;

    @Column(nullable = false)
    @Size(min = 1, max = 500)
    private String description;

    @ManyToOne
    @JoinColumn(name = "skill_id", nullable = false)
    private SkillEntity skill;

    @ManyToOne
    @JoinColumn(name = "developer_id", nullable = false)
    private DeveloperEntity developer;

    @Column(nullable = false)
    private int position;

    @Column(nullable = false, unique = true, length = 30)
    private String publicKey;

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

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

    public SkillEntity getSkill() {
	return skill;
    }

    public void setSkill(SkillEntity skill) {
	this.skill = skill;
    }

    public DeveloperEntity getDeveloper() {
	return developer;
    }

    public void setDeveloper(DeveloperEntity developer) {
	this.developer = developer;
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
