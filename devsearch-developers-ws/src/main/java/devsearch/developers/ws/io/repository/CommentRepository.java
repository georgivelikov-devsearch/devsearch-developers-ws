package devsearch.developers.ws.io.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import devsearch.developers.ws.io.entity.CommentEntity;

@Repository
public interface CommentRepository extends CrudRepository<CommentEntity, Long> {

    public CommentEntity findByCommentId(String commentId);

    @Transactional
    @Query(value = "SELECT c FROM CommentEntity c where c.projectId = :projectId")
    public List<CommentEntity> findCommentsByProjectId(String projectId);
}
