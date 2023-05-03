package com.jobtang.sourcecompany.api.community.dto;

import com.jobtang.sourcecompany.api.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateCommunityRequest {
  private String communityType;

  private String title;

  private String content;

  private User user;

  private int totalView;

  private int yesterdayView;
}
