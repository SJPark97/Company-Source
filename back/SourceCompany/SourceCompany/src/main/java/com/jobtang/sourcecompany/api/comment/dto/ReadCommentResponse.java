package com.jobtang.sourcecompany.api.comment.dto;

import com.jobtang.sourcecompany.api.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ReadCommentResponse {
  private Long commentId ;
  private Long commentGroup;
  private String nickname;
  private Long parent;
  private String content;

  public static ReadCommentResponse EntityDTO (Comment comment){
    return ReadCommentResponse.builder()
            .commentId(comment.getId())
            .content(comment.getContent())
            .nickname(comment.getUser().getNickname())
            .parent(comment.getParent())
            .commentGroup(comment.getCommentGroup())
            .build();
  }


}
