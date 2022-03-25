package devsearch.profiles.ws.io.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import devsearch.profiles.ws.io.entity.ProfileEntity;

@Repository
public interface ProfileRepository extends PagingAndSortingRepository<ProfileEntity, Long> {

    public ProfileEntity findByProfileId(String profileId);

    public ProfileEntity findByUsername(String username);

    @Transactional
    @Query(value = "SELECT p FROM ProfileEntity p where (p.firstName LIKE %:searchText% OR p.lastName LIKE %:searchText%)")
    Page<ProfileEntity> findAllByText(Pageable pageable, String searchText);
}
