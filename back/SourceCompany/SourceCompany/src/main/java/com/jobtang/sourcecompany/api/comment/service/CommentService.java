package com.jobtang.sourcecompany.api.comment.service;

import com.jobtang.sourcecompany.api.comment.dto.CreateCommentRequest;

public interface CommentService {

  Long createComment( Long userId, CreateCommentRequest createCommentRequest);
<<<<<<< HEAD

  void deleteComment(Long commentId);
=======
>>>>>>> dcd36873a727d1402c37c4c0deafe32f26e4f324
}
