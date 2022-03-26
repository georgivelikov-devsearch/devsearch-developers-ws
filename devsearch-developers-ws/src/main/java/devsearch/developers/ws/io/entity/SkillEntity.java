package devsearch.developers.ws.io.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "skills")
public class SkillEntity implements Serializable {

    private static final long serialVersionUID = 827809452719953633L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true)
    private String skillId;

    @Column(nullable = false, unique = true)
    @Size(min = 1, max = 50)
    private String skillName;

    @OneToMany(mappedBy = "skill")
    private Set<SkillDescriptionEntity> skillDescriptions;

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public String getSkillId() {
	return skillId;
    }

    public void setSkillId(String skillId) {
	this.skillId = skillId;
    }

    public String getSkillName() {
	return skillName;
    }

    public void setSkillName(String skillName) {
	this.skillName = skillName;
    }

    public Set<SkillDescriptionEntity> getSkillDescriptions() {
	return skillDescriptions;
    }

    public void setSkillDescriptions(Set<SkillDescriptionEntity> skillDescriptions) {
	this.skillDescriptions = skillDescriptions;
    }

}
