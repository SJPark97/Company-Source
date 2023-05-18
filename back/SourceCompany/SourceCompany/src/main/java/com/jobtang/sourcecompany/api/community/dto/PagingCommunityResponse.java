package com.jobtang.sourcecompany.api.community.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class PagingCommunityResponse {
  private int pageTotal;
  private List<ReadAllCommunityResponse> readAllCommunityResponses;

}
