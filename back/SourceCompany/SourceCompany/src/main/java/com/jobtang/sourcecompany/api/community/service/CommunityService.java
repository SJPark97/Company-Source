package com.jobtang.sourcecompany.api.community.service;

import com.jobtang.sourcecompany.api.community.dto.CreateCommunityRequest;
import com.jobtang.sourcecompany.api.user.entity.User;

public interface CommunityService {
  void createCommunity(User user, CreateCommunityRequest createCommunityRequest) throws Exception;
}
