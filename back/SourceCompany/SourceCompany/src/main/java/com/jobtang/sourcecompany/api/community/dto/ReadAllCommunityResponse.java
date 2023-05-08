package com.jobtang.sourcecompany.api.community.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ReadAllCommunityResponse {
  private String title;
  private String userName;
  private int viewCount;
  private LocalDateTime createdAt;
//  private int commentCount;



}
