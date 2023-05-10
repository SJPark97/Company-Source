package com.jobtang.sourcecompany.api.community.dto;

import com.jobtang.sourcecompany.api.community.entity.Community;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ReadAllCommunityResponse {
  private Long communityId;
  private String title;
  private String userName;
  private int viewCount;
  private LocalDateTime createdDate;
  private int commentCount;
  public static ReadAllCommunityResponse EntityToDTO (Community community , int viewCount){
    return ReadAllCommunityResponse.builder()
            .communityId(community.getId())
            .title(community.getTitle())
            .userName(community.getUser().getNickname())
<<<<<<< HEAD
            .viewCount(viewCount+community.getTotalView()+community.getYesterdayView())
=======
            .viewCount(viewCount+community.getTotalView())
>>>>>>> dcd36873a727d1402c37c4c0deafe32f26e4f324
            .createdDate(community.getCreatedDate())
            .commentCount(community.getComments().size())
            .build();
  }



}
