package devsearch.developers.ws.io.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import devsearch.developers.ws.io.entity.DeveloperEntity;

@Repository
public interface DeveloperRepository extends PagingAndSortingRepository<DeveloperEntity, Long> {

    public DeveloperEntity findByDeveloperId(String developerId);

    public DeveloperEntity findByUsername(String username);

    @Transactional
    @Query(value = "SELECT d FROM DeveloperEntity d where (d.firstName LIKE %:searchText% OR d.lastName LIKE %:searchText%)")
    Page<DeveloperEntity> findAllByText(Pageable pageable, String searchText);
}
