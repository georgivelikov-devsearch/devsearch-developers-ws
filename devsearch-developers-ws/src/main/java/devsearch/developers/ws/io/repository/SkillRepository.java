package devsearch.developers.ws.io.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import devsearch.developers.ws.io.entity.SkillEntity;

@Repository
public interface SkillRepository extends CrudRepository<SkillEntity, Long> {

    public SkillEntity findBySkillId(String skillId);

    public SkillEntity findBySkillName(String skillName);
}
