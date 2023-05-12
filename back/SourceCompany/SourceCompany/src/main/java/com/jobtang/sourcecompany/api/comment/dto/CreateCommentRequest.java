package com.jobtang.sourcecompany.api.comment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateCommentRequest {
  private String content;

  // 달린 communityId를 따라간다.
  private Long communityId;

  // commentId 값을 따라간다.
  private Long commentGroup;

  // 부모는 1 자식은 0
  private Long parent;

}
