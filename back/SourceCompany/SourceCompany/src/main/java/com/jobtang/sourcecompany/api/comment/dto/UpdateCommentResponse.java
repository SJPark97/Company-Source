package com.jobtang.sourcecompany.api.comment.dto;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UpdateCommentResponse {
  private String content;
  private Long commentId;


}
