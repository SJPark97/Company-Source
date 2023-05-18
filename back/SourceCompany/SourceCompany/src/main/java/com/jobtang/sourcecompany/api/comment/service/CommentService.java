package com.jobtang.sourcecompany.api.comment.service;

import com.jobtang.sourcecompany.api.comment.dto.CreateCommentRequest;
import com.jobtang.sourcecompany.api.comment.dto.UpdateCommentRequest;
import com.jobtang.sourcecompany.api.comment.dto.UpdateCommentResponse;

public interface CommentService {

  Long createComment( Long userId, CreateCommentRequest createCommentRequest);

  void deleteComment(Long userId ,Long commentId);

  UpdateCommentResponse updateComment(Long userId ,UpdateCommentRequest updateCommentRequest);

}
