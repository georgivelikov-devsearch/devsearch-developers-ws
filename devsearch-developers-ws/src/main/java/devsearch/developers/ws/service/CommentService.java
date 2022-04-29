package devsearch.developers.ws.service;

import java.util.List;

import devsearch.common.exception.DevsearchApiException;
import devsearch.developers.ws.shared.dto.CommentDto;

public interface CommentService {

    public CommentDto createComment(CommentDto commentDto) throws DevsearchApiException;

    public List<CommentDto> getCommentsByProjectId(String projectId) throws DevsearchApiException;
}
