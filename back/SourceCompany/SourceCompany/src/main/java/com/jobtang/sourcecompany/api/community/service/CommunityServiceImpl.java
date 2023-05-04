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
  public void createCommunity(User user, CreateCommunityRequest createCommunityRequest) throws Exception {

    // user 확인을 위한 코드
    // user.isActive 값이 false 이거나 , null 인 경우
    if( user == null || user.isActive() == false) {
      throw new Exception("올바른 유저가 아닙니다");
    }
    // Builder 로 community 객체 생성
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

  @Override
  public void readCommunity(Long communityId) {

    // 게시판이 있는 지 확인

    // 없다면 에러 던지기

    // 있다면 가져오고 ,

    // 레디스에 조회수 기록해두고

    // 레디스의 조회수와 해당 게시판의 토탈 조회수를 더한 값을 조회수로 기록

    //


  }
}
