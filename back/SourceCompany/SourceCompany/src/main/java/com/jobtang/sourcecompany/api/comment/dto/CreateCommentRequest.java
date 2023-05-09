package com.jobtang.sourcecompany.api.comment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateCommentRequest {
  private String content;

  private Long communityId;

  private Long commentGroup;

  private int commentDepth;

}
