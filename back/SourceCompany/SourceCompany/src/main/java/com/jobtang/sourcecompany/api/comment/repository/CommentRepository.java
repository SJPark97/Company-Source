package com.jobtang.sourcecompany.api.comment.repository;

import com.jobtang.sourcecompany.api.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

<<<<<<< HEAD
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment , Long> {
  List<Comment> findAllByIdAndIsActiveTrueOrderByCommentGroupAscCreatedDateAsc(Long commentId);
=======
public interface CommentRepository extends JpaRepository<Comment , Long> {
>>>>>>> dcd36873a727d1402c37c4c0deafe32f26e4f324
}
