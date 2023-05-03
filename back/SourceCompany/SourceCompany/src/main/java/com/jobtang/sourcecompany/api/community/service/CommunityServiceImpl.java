package com.jobtang.sourcecompany.api.community.service;

import com.jobtang.sourcecompany.api.community.dto.CreateCommunityRequest;
import com.jobtang.sourcecompany.api.community.entity.Community;
import com.jobtang.sourcecompany.api.community.repository.CommunityRepository;
import com.jobtang.sourcecompany.api.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService{
  private  final CommunityRepository communityRepository;
  private final ModelMapper mapper ;
  /**
   * 커뮤니티를 생성하는 메소드
   * @param user
   * @param createCommunityRequest
   */
  @Override
  public void createCommunity(User user, CreateCommunityRequest createCommunityRequest) {
    Community community = Community.builder()
            .communityType(createCommunityRequest.getCommunityType())
            .content(createCommunityRequest.getContent())
            .title(createCommunityRequest.getTitle())
            .user(user)
            .yesterdayView(0)
            .totalView(0)
            .build();
    communityRepository.save(community);
  }
}
