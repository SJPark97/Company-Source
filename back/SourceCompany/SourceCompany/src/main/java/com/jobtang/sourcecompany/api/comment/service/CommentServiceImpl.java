package com.jobtang.sourcecompany.api.comment.service;

import com.jobtang.sourcecompany.api.comment.dto.CreateCommentRequest;
import com.jobtang.sourcecompany.api.comment.dto.UpdateCommentRequest;
import com.jobtang.sourcecompany.api.comment.dto.UpdateCommentResponse;
import com.jobtang.sourcecompany.api.comment.entity.Comment;
import com.jobtang.sourcecompany.api.comment.repository.CommentRepository;
import com.jobtang.sourcecompany.api.community.entity.Community;
import com.jobtang.sourcecompany.api.community.repository.CommunityRepository;
import com.jobtang.sourcecompany.api.exception.CustomException;
import com.jobtang.sourcecompany.api.exception.ErrorCode;
import com.jobtang.sourcecompany.api.user.entity.User;
import com.jobtang.sourcecompany.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {


  private final CommentRepository commentRepository;
  private final CommunityRepository communityRepository;

  private final UserRepository userRepository;


  @Override
  @Transactional
  public Long createComment(Long userId, CreateCommentRequest createCommentRequest) {
    // 유저 벨리드 체크
    User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(ErrorCode.USER_EXISTS));
    // 커뮤니티 밸리드 체크
    Community community = communityRepository.findById(createCommentRequest.getCommunityId()).orElseThrow(() -> new CustomException(ErrorCode.COMM_EXISTS));

    // 코멘트 생성
    Comment comment = Comment.builder()
            .parent(createCommentRequest.getParent())
            .commentGroup(createCommentRequest.getCommentGroup())
            .content(createCommentRequest.getContent())
            .user(user)
            .community(community)
            .build();

    Long commentId = 0L;
    commentId = commentRepository.save(comment).getId();
    // 만약 부모라면
    if (comment.getCommentGroup() == 0L) {
      comment.updateCommentGroup();
      commentRepository.save(comment);
    }
    return commentId;
  }

  @Override
  @Transactional
  public void deleteComment(Long commentId) {
    Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CustomException(ErrorCode.COMM_COMMENT_EXISTS));

    //부모 댓글인 경우
    if(comment.getParent()==0){

    }
    //자식 댓글인 경운
    else{
      comment.deleteEntity();
    }
  }

  @Override
  @Transactional
  public UpdateCommentResponse updateComment(UpdateCommentRequest updateCommentRequest) {
    Comment comment = commentRepository.findById(updateCommentRequest.getCommentId()).orElseThrow(() -> new CustomException(ErrorCode.COMM_COMMENT_EXISTS));
    comment.setContent(updateCommentRequest.getContent());
    return UpdateCommentResponse.builder()
            .commentId(comment.getId())
            .content(comment.getContent())
            .build();
  }


}