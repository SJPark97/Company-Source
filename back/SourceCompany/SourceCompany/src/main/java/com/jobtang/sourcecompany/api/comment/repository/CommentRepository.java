package com.jobtang.sourcecompany.api.comment.repository;

import com.jobtang.sourcecompany.api.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment , Long> {
  List<Comment> findAllByCommunityIdAndIsActiveTrueOrderByCommentGroupAscCreatedDateAsc(Long communityId);

  List<Comment> findByCommentGroupAndIsActiveTrue(Long commentGroup);

  // 해당 그룹의 부모가 삭제된 경우 조회
  Optional<Comment>  findByCommentGroupAndParentAndContentAndIsActiveTrue (Long commentGroup,Long parent,String content);

  // 모든 자식 댓글들 구하는 메소드
  List<Comment> findByCommentGroupAndIsActiveTrueAndCommunityIdAndParent (Long commentGroup,Long communityId, Long parent);
  Optional<Comment> findByIdAndIsActiveTrue(Long commentId);
}
