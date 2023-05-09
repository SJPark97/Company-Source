package com.jobtang.sourcecompany.api.comment.service;

import com.jobtang.sourcecompany.api.comment.dto.CreateCommentRequest;
import com.jobtang.sourcecompany.api.comment.entity.Comment;
import com.jobtang.sourcecompany.api.comment.repository.CommentRepository;
import com.jobtang.sourcecompany.api.community.entity.Community;
import com.jobtang.sourcecompany.api.community.repository.CommunityRepository;
import com.jobtang.sourcecompany.api.exception.CustomException;
import com.jobtang.sourcecompany.api.exception.ErrorCode;
import com.jobtang.sourcecompany.api.user.entity.User;
import com.jobtang.sourcecompany.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements  CommentService {

  private final CommentRepository commentRepository;
  private final CommunityRepository communityRepository;

  private final UserRepository userRepository;


  @Override
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

    Long commentId =0L;
    commentId = commentRepository.save(comment).getId();
    // 만약 부모라면
//    if(comment.){
//
//    }

    return commentId;
  }
}