package com.jobtang.sourcecompany.api.community.service;

import com.jobtang.sourcecompany.api.community.dto.*;
import com.jobtang.sourcecompany.api.user.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommunityService {
  void createCommunity(String communityType, User user, CreateCommunityRequest createCommunityRequest) throws Exception;

  List<ReadAllCommunityResponse> searchCommunity(String content , String  type , Pageable pageable );

  ReadCommunityDetailResponse readCommunityDetail(String communityType,Long communityId);
  void deleteCommunity(Long communityId);

  List<ReadAllCommunityResponse> readAllCommunity(Pageable pageable);

  UpdateCommunityResponse updateCommunity(UpdateCommunityRequest updateCommunityRequest);


  void updateViewCommunity();
}
