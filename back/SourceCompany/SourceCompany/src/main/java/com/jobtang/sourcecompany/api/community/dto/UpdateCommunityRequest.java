package com.jobtang.sourcecompany.api.community.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateCommunityRequest {
  private Long id;
  private String title;

  private String content;
}
