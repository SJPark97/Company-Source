package com.jobtang.sourcecompany.api.community.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ReadAllSearchResponse {
  private PagingCommunityResponse corp_search ;
  private PagingCommunityResponse free_search ;
}
