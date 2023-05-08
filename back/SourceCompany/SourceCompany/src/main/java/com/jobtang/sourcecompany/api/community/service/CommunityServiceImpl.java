package com.jobtang.sourcecompany.api.community.service;

import com.jobtang.sourcecompany.api.community.dto.*;
import com.jobtang.sourcecompany.api.community.entity.Community;
import com.jobtang.sourcecompany.api.community.repository.CommunityRepository;
import com.jobtang.sourcecompany.api.exception.CustomException;
import com.jobtang.sourcecompany.api.exception.ErrorCode;
import com.jobtang.sourcecompany.api.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {

  private final RedisTemplate<String, Integer> integerRedisTemplate;
  private final CommunityRepository communityRepository;
  private final ModelMapper mapper;

  /**
   * 커뮤니티를 생성하는 메소드
   *
   * @param user
   * @param createCommunityRequest
   */
  @Override
  @Transactional
  public void createCommunity(User user, CreateCommunityRequest createCommunityRequest) throws Exception {

    // user 확인을 위한 코드
    // user.isActive 값이 false 이거나 , null 인 경우
    if (user == null || user.isActive() == false) {
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
  public ReadCommunityDetailResponse readCommunityDetail(Long communityId) {

    // 게시판이 있는 지 확인 없다면 에러 던지기 있다면 가져오고 ,
    Community community = communityRepository.findById(communityId).orElseThrow(() -> new CustomException(ErrorCode.COMM_EXISTS));
    if (community.isActive() == false) {
      throw new CustomException(ErrorCode.COMM_DELETED);
    }

    // 레디스에 조회수 기록해두고
    String key = "viewComm" + community.getId();
    ValueOperations<String, Integer> valueOperations = integerRedisTemplate.opsForValue();
    if (valueOperations.get(key) == null) {
      valueOperations.set(key, 1);
    } else {
      Integer viewCnt = valueOperations.get(key);
      valueOperations.set(key, viewCnt + 1);
    }
//    레디스에서 key로 밸류 가져오는 코드
    int viewcnt = integerRedisTemplate.opsForValue().get(key);


    // 레디스의 조회수와 해당 게시판의 토탈 조회수를 더한 값을 조회수로 기록
    viewcnt += community.getTotalView();
    // 해당 커뮤니티의 댓글들을 List<DTO> 로 바꾸는 부분

    // 마지막에 ReadCommunityResponse 로 바꾸는 부분
    return ReadCommunityDetailResponse.EntityToDTO(community, viewcnt);
  }


  @Override
  @Transactional
  public void deleteCommunity(Long communityId) {
    Community community = communityRepository.findById(communityId).orElseThrow(() -> new CustomException(ErrorCode.COMM_EXISTS));
    // 이미 삭제된 게시글이였던 경우
    if (community.isActive() == false) {
      throw new CustomException(ErrorCode.COMM_DELETED);
    }
    community.deleteEntity();
  }

  @Override
  public List<ReadAllCommunityResponse> readAllCommunity(Pageable pageable) {
    // 인자로 받은 Pageable 객체의 정보를 토대로 DB에서 Community값들 가져오기
    Page<Community> communities = communityRepository.findAllByIsActiveTrue(pageable);
    // 받아온 정보에 redis의 조회수를 더하는 코드
    return communities.stream()
            .map(community -> {
              // 레디스에 저장된 해당 커뮤니티의 key값
              String key = "viewComm" + community.getId();
              int redisViewCnt = 0;
              ValueOperations<String, Integer> valueOperations = integerRedisTemplate.opsForValue();
              if (valueOperations.get(key) != null) {
                redisViewCnt = valueOperations.get(key);
              }
              return ReadAllCommunityResponse.EntityToDTO(community, redisViewCnt);
            })
            .collect(Collectors.toList());

  }

  @Override
  @Transactional
  public UpdateCommunityResponse updateCommunity(UpdateCommunityRequest updateCommunityRequest) {
    Community community = communityRepository
            .findById(updateCommunityRequest.getId()).orElseThrow(() -> new CustomException(ErrorCode.COMM_EXISTS));
    community.setContent(updateCommunityRequest.getContent());
    community.setTitle(updateCommunityRequest.getTitle());
    return UpdateCommunityResponse.builder()
            .id(community.getId())
            .content(community.getContent())
            .title(community.getTitle())
            .build();
  }
}
