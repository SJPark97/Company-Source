package com.jobtang.sourcecompany.api.community.service;

import com.jobtang.sourcecompany.api.community.dto.*;
import com.jobtang.sourcecompany.api.user.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CommunityService {
  Long createCommunity(String communityType, Long userId, CreateCommunityRequest createCommunityRequest) throws Exception;

  PagingCommunityResponse searchCommunity(String communityType , String content , String  type , Pageable pageable );

  ReadCommunityDetailResponse readCommunityDetail(Long userId ,String communityType,Long communityId);
  void deleteCommunity(Long userId , Long communityId);

  PagingCommunityResponse readAllCommunity(String type , String sort , Pageable pageable);

  UpdateCommunityResponse updateCommunity(Long userId ,UpdateCommunityRequest updateCommunityRequest);

  ReadRandingCommunityResponse readRandingCommunity(int standard ,Pageable pageable);

  void updateViewCommunity();
  void schedule();
  int getTotalPage();

  // 커뮤니티의 조회수만을 늘려주는 메소드
  int addViewCommunity(Long communityId);

}
