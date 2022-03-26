package devsearch.developers.ws.io.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import devsearch.developers.ws.io.entity.SkillDescriptionEntity;

@Repository
public interface SkillDescriptionRepository extends PagingAndSortingRepository<SkillDescriptionEntity, Long> {
    public SkillDescriptionEntity findBySkillDescriptionId(String skillDescriptionId);
}
