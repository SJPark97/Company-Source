package com.jobtang.sourcecompany.api.community.service;

import com.jobtang.sourcecompany.api.community.dto.*;
import com.jobtang.sourcecompany.api.user.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;

public interface CommunityService {
  void createCommunity(String communityType, Long userId, CreateCommunityRequest createCommunityRequest) throws Exception;

  List<ReadAllCommunityResponse> searchCommunity(String communityType , String content , String  type , Pageable pageable );

  ReadCommunityDetailResponse readCommunityDetail(Long userId ,String communityType,Long communityId);
  void deleteCommunity(Long userId , Long communityId);

  List<ReadAllCommunityResponse> readAllCommunity(String type , String sort ,Pageable pageable);

  UpdateCommunityResponse updateCommunity(Long userId ,UpdateCommunityRequest updateCommunityRequest);

  ReadRandingCommunityResponse readRandingCommunity();

  void updateViewCommunity();
  void schedule();
}
