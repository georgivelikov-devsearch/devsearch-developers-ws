package devsearch.developers.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import devsearch.common.exception.DevsearchApiException;
import devsearch.common.utils.Utils;
import devsearch.developers.ws.io.entity.CommentEntity;
import devsearch.developers.ws.io.entity.DeveloperEntity;
import devsearch.developers.ws.io.repository.CommentRepository;
import devsearch.developers.ws.io.repository.DeveloperRepository;
import devsearch.developers.ws.service.CommentService;
import devsearch.developers.ws.shared.dto.CommentDto;
import devsearch.developers.ws.shared.mapper.ModelMapper;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private DeveloperRepository developerRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public CommentDto createComment(CommentDto commentDto) throws DevsearchApiException {
	CommentEntity commentEntity = mapper.map(commentDto, CommentEntity.class);
	commentEntity.setCommentId(Utils.generateId());
	commentEntity.setPublicKey(Utils.generatePublicKey());

	DeveloperEntity developerEntity = developerRepository.findByDeveloperId(commentDto.getDeveloperId());
	commentEntity.setDeveloper(developerEntity);

	CommentEntity newCommentEntity = commentRepository.save(commentEntity);

	CommentDto returnValue = mapper.map(newCommentEntity, CommentDto.class);

	returnValue = addAuthorInfo(returnValue, newCommentEntity);

	return returnValue;
    }

    @Override
    public List<CommentDto> getCommentsByProjectId(String projectId) throws DevsearchApiException {
	List<CommentEntity> commentsForProject = commentRepository.findCommentsByProjectId(projectId);

	List<CommentDto> commentsForProjectDto = new ArrayList<>();
	for (CommentEntity commentEntity : commentsForProject) {
	    CommentDto commentDto = mapper.map(commentEntity, CommentDto.class);
	    commentDto = addAuthorInfo(commentDto, commentEntity);
	    commentsForProjectDto.add(commentDto);
	}

	return commentsForProjectDto;
    }

    private CommentDto addAuthorInfo(CommentDto commentDto, CommentEntity commentEntity) {
	commentDto.setAuthor(commentEntity.getDeveloper().getUsername());
	commentDto.setAuthorFullname(
		commentEntity.getDeveloper().getFirstName() + " " + commentEntity.getDeveloper().getLastName());
	commentDto.setAuthorPictureUrl(commentEntity.getDeveloper().getDeveloperPictureUrl());

	return commentDto;
    }

    @Override
    public void deleteComment(String commentId) throws DevsearchApiException {
	CommentEntity comment = commentRepository.findByCommentId(commentId);

	commentRepository.delete(comment);
    }
}
