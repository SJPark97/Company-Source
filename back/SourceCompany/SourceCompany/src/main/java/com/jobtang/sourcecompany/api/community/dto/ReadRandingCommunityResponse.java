package com.jobtang.sourcecompany.api.community.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ReadRandingCommunityResponse {
  private List<ReadAllCommunityResponse> corpHot;
  private List<ReadAllCommunityResponse> freeHot;

}
