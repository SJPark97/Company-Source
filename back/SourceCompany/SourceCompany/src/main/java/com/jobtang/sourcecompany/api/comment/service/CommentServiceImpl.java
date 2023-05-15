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
  public void deleteComment(Long userId ,Long commentId) {
    Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new CustomException(ErrorCode.COMM_COMMENT_EXISTS));
    // 작성자와 삭제하려는 사람이 같지않으면 에러
    if(comment.getUser().getId() != userId){
      throw  new CustomException(ErrorCode.COMM_NOT_WRITER);
    }
    //부모 댓글인 경우
    if(comment.getParent()==1){
      // 자식이 있으면
      if(commentRepository.findByCommentGroupAndIsActiveTrue(comment.getCommentGroup()).size()>1){
        comment.setContent("삭제된 댓글 입니다.");
      }
      //자식이 없으면
      else{
        comment.deleteEntity();
      }
    }
    //자식 댓글인 경운
    else{
      // 부모도 없고 , 혼자남은 자식이였다면
      if(
              // 현재 그룹의 부모가 삭제된 상태인지 확인
              commentRepository.findByCommentGroupAndParentAndContentAndIsActiveTrue(comment.getCommentGroup(),1L, "삭제된 댓글 입니다.").isPresent() &&
                      // 현재 그룹의 자식들 중 삭제된 값이 하나밖에 없는지 확인
                      commentRepository.findByCommentGroupAndIsActiveTrueAndCommunityIdAndParent(comment.getCommentGroup(),comment.getCommunity().getId(), 0L).size()==1&&
                      // 그 자식이 현재 comment 인지 확인
                      commentRepository.findByIdAndIsActiveTrue(comment.getId()).isPresent()
      ){
        //모두 충족한다면 ,부모댓글과 자식댓글 모두 삭제
        commentRepository.findById(comment.getCommentGroup()).get().deleteEntity();
        comment.deleteEntity();
      }
      else{
        comment.deleteEntity();

      }
    }
  }

  @Override
  @Transactional
  public UpdateCommentResponse updateComment(Long userId ,UpdateCommentRequest updateCommentRequest) {
    Comment comment = commentRepository.findById(updateCommentRequest.getCommentId()).orElseThrow(() -> new CustomException(ErrorCode.COMM_COMMENT_EXISTS));
    if(userId != comment.getUser().getId()){
      throw new CustomException(ErrorCode.COMM_NOT_WRITER);
    }
    comment.setContent(updateCommentRequest.getContent());
    return UpdateCommentResponse.builder()
            .commentId(comment.getId())
            .content(comment.getContent())
            .build();
  }


}