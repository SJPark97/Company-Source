package com.jobtang.sourcecompany.api.community.dto;

import com.jobtang.sourcecompany.api.comment.dto.ReadCommentResponse;
import com.jobtang.sourcecompany.api.community.entity.Community;
import lombok.*;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ReadCommunityDetailResponse {
  private  Long communityId ;

  private String title;

  private String content;

  private String userName;

//  private String userName;
  private int viewCount;
//  List<ReadCommentResponse> comments;
  public static ReadCommunityDetailResponse EntityToDTO (Community community , int viewCount) {
    ReadCommunityDetailResponse readCommentResponse = ReadCommunityDetailResponse.builder()
            .userName(community.getUser().getNickname())
            .title(community.getTitle())
            .communityId(community.getId())
            .content(community.getContent())
            .viewCount(viewCount)
            .build();
    return readCommentResponse;
  }

}
