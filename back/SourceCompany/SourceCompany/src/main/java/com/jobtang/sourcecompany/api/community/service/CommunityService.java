package com.jobtang.sourcecompany.api.community.service;

import com.jobtang.sourcecompany.api.community.dto.*;
import com.jobtang.sourcecompany.api.user.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;

public interface CommunityService {
  void createCommunity(String communityType, Long userId, CreateCommunityRequest createCommunityRequest) throws Exception;

  List<ReadAllCommunityResponse> searchCommunity(String communityType , String content , String  type , Pageable pageable );

  ReadCommunityDetailResponse readCommunityDetail(String communityType,Long communityId);
  void deleteCommunity(Long communityId);

  List<ReadAllCommunityResponse> readAllCommunity(Pageable pageable);

  UpdateCommunityResponse updateCommunity(UpdateCommunityRequest updateCommunityRequest);

  ReadRandingCommunityResponse readRandingCommunity();

  void updateViewCommunity();
<<<<<<< HEAD
  void schedule();
=======
>>>>>>> dcd36873a727d1402c37c4c0deafe32f26e4f324
}
