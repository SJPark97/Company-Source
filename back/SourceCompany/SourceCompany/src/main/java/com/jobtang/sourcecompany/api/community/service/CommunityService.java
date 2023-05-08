package com.jobtang.sourcecompany.api.community.service;

import com.jobtang.sourcecompany.api.community.dto.*;
import com.jobtang.sourcecompany.api.user.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommunityService {
  void createCommunity(User user, CreateCommunityRequest createCommunityRequest) throws Exception;

  ReadCommunityDetailResponse readCommunityDetail(Long communityId);
  void deleteCommunity(Long communityId);

  List<ReadAllCommunityResponse> readAllCommunity(Pageable pageable);

  UpdateCommunityResponse updateCommunity(UpdateCommunityRequest updateCommunityRequest);
}
