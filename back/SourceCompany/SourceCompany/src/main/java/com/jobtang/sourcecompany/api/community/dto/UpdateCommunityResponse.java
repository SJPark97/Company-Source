package com.jobtang.sourcecompany.api.community.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class UpdateCommunityResponse {
  private Long id;
  private  String title;
  private  String content;
}
