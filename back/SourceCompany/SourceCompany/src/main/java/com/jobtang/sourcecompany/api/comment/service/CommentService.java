package com.jobtang.sourcecompany.api.comment.service;

import com.jobtang.sourcecompany.api.comment.dto.CreateCommentRequest;

public interface CommentService {

  Long createComment( Long userId, CreateCommentRequest createCommentRequest);
}
