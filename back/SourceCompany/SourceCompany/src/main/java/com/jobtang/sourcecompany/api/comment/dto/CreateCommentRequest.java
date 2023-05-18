package com.jobtang.sourcecompany.api.comment.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@ApiModel(description = "댓글 작성")
public class CreateCommentRequest {
  @ApiModelProperty(value = "댓글 내용", example = "오늘 뭐하냐")
  private String content;

  // 달린 communityId를 따라간다.
  @ApiModelProperty(value = "댓글이 달릴 게시글의 아이디 (게시글의 CommunityId)", example = "24",notes = "")
  private Long communityId;

  @ApiModelProperty(value="그룹은 댓글과 대댓글을 묶는 개념.\n 댓글의 경우 0 , 대댓글의 경우 댓글의 commentGroup 입력 ", example = "1")
  // commentId 값을 따라간다.
  private Long commentGroup;

  @ApiModelProperty(value = "댓글인 경우 0 , 대댓글인 경우 1", example = "0" , allowableValues = "0,1")

  // 부모는 1 자식은 0
  private Long parent;

}
