package com.jobtang.sourcecompany.api.community.service;

import com.jobtang.sourcecompany.api.community.dto.*;
import com.jobtang.sourcecompany.api.community.entity.Community;
import com.jobtang.sourcecompany.api.community.repository.CommunityRepository;
import com.jobtang.sourcecompany.api.corp.entity.Corp;
import com.jobtang.sourcecompany.api.exception.CustomException;
import com.jobtang.sourcecompany.api.exception.ErrorCode;
import com.jobtang.sourcecompany.api.likes.entity.Likes;
import com.jobtang.sourcecompany.api.likes.repository.LikesRepository;
import com.jobtang.sourcecompany.api.user.entity.User;
import com.jobtang.sourcecompany.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {
  private final UserRepository userRepository;

  private final RedisTemplate<String, Integer> integerRedisTemplate;
  private final CommunityRepository communityRepository;
  private final LikesRepository likesRepository;
  private final ModelMapper mapper;

  /**
   * 커뮤니티를 생성하는 메소드
   *
   * @param createCommunityRequest
   */
  @Override
  @Transactional
  public void createCommunity(String communityType, Long userId, CreateCommunityRequest createCommunityRequest) throws Exception {
    User user = userRepository.findById(userId).get();
    // user 확인을 위한 코드
    // user.isActive 값이 false 이거나 , null 인 경우
    if (user == null || user.isActive() == false) {
      throw new Exception("올바른 유저가 아닙니다");
    }
    // Builder 로 community 객체 생성
    Community community = Community.builder()
            .communityType(communityType)
            .content(createCommunityRequest.getContent())
            .title(createCommunityRequest.getTitle())
            .user(user)
            .yesterdayView(0)
            .likesCnt(0)
            .totalView(0)
            .build();
    communityRepository.save(community);
  }

  @Override
  public List<ReadAllCommunityResponse> searchCommunity(String communityType, String content, String type, Pageable pageable) {
    if (type.equals("content")) {
      Page<Community> communities = communityRepository.findAllByCommunityTypeAndContentContainingAndIsActiveTrue(communityType, content, pageable);
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
    } else if (type.equals("title")) {
      Page<Community> communities = communityRepository.findAllByCommunityTypeAndTitleContainingAndIsActiveTrue(communityType, content, pageable);
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
    } else {
      throw new CustomException(ErrorCode.WRONG_INPUT_DATA);
    }
  }


  @Override
  public ReadCommunityDetailResponse readCommunityDetail(Long userId, String communityType, Long communityId) {

    // 게시판이 있는 지 확인 없다면 에러 던지기 있다면 가져오고 ,
    Community community = communityRepository.findById(communityId).orElseThrow(() -> new CustomException(ErrorCode.COMM_EXISTS));
    if (community.isActive() == false) {
      throw new CustomException(ErrorCode.COMM_DELETED);
    }
    if (!community.getCommunityType().equals(communityType)) {
      throw new CustomException(ErrorCode.COMM_WRONG_TYPE);
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
    viewcnt += community.getTotalView() + community.getYesterdayView();
    // 해당 커뮤니티의 댓글들을 List<DTO> 로 바꾸는 부분

    // 마지막에 ReadCommunityResponse 로 바꾸는 부분

    // 현재 유저가 게스트인지 로그인된 유저인지에 따라 분기
    if(userId !=0  )  {
      //로그인 한 유저라면 현재 게시물에 좋아요한 기록이있는 지 찾아서
      Optional<Likes> likes=  likesRepository.findByUserIdAndCommunityId(userId ,communityId);
      // 있고 , 삭제된것도 아니라면
      if(likes.isPresent() && likes.get().isActive()==true){
        return ReadCommunityDetailResponse.EntityToDTO(community, viewcnt,true);
      }
      else{
        // 로그인은 했지만 , 게시물에 좋아요는 안했다면
        return ReadCommunityDetailResponse.EntityToDTO(community, viewcnt,false);
      }
    }
    else{
      // 로그인안한 유저라면
      return ReadCommunityDetailResponse.EntityToDTO(community, viewcnt,false);
    }
  }


  @Override
  @Transactional
  public void deleteCommunity(Long userId , Long communityId) {
    Community community = communityRepository.findById(communityId).orElseThrow(() -> new CustomException(ErrorCode.COMM_EXISTS));
    // 이미 삭제된 게시글이였던 경우
    if (community.isActive() == false) {
      throw new CustomException(ErrorCode.COMM_DELETED);
    }
    // 만약 로그인한 유저와 삭제할 게시판의 작성자가 서로 다를 경우
    if(community.getUser().getId() != userId){
      throw new CustomException(ErrorCode.COMM_NOT_WRITER);
    }
    community.deleteEntity();
  }

  @Override
  public PagingCommunityResponse readAllCommunity(String type , String sort , Pageable pageable) {
    // 인자로 받은 Pageable 객체의 정보를 토대로 DB에서 Community값들 가져오기
    // 크리에이티드  시간 순으로 정렬
    Page<Community> communities = communityRepository.findAllByIsActiveAndCommunityType(true, type, pageable);
    List<ReadAllCommunityResponse> readAllCommunityResponses = communities.stream()
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
    int pageTotal = communities.getTotalPages();

    // 받아온 정보에 redis의 조회수를 더하는 코드
    return new PagingCommunityResponse(pageTotal , readAllCommunityResponses);

  }

  @Override
  @Transactional
  public UpdateCommunityResponse updateCommunity(Long userId ,UpdateCommunityRequest updateCommunityRequest) {
    Community community = communityRepository
            .findById(updateCommunityRequest.getId()).orElseThrow(() -> new CustomException(ErrorCode.COMM_EXISTS));
    // 작성한 유저와 , 로그인한 유저가 다를 경우 에러 출력
    if(community.getUser().getId() != userId){
      throw new CustomException(ErrorCode.COMM_NOT_WRITER);
    }

    community.setContent(updateCommunityRequest.getContent());
    community.setTitle(updateCommunityRequest.getTitle());
    return UpdateCommunityResponse.builder()
            .id(community.getId())
            .content(community.getContent())
            .title(community.getTitle())
            .build();
  }

  @Override
  public ReadRandingCommunityResponse readRandingCommunity() {
    // corpHot
    List<ReadAllCommunityResponse> corpHot = communityRepository.findByCommunityTypeOrderByLikeCntTop8("기업")
            .stream().map(community -> {
              String key = "viewComm" + community.getId();
              int redisViewCnt = 0;
              ValueOperations<String, Integer> valueOperations = integerRedisTemplate.opsForValue();
              if (valueOperations.get(key) != null) {
                redisViewCnt = valueOperations.get(key);
              }
              return ReadAllCommunityResponse.EntityToDTO(community, redisViewCnt);
            }).collect(Collectors.toList());
    // freeHot
    List<ReadAllCommunityResponse> freeHot = communityRepository.findByCommunityTypeOrderByLikeCntTop8("자유")
            .stream().map(community -> {
              String key = "viewComm" + community.getId();
              int redisViewCnt = 0;
              ValueOperations<String, Integer> valueOperations = integerRedisTemplate.opsForValue();
              if (valueOperations.get(key) != null) {
                redisViewCnt = valueOperations.get(key);
              }
              return ReadAllCommunityResponse.EntityToDTO(community, redisViewCnt);
            }).collect(Collectors.toList());

    return new ReadRandingCommunityResponse(corpHot, freeHot);
  }

  @Override
  @Transactional
  public void updateViewCommunity() {
    List<Community> communities = communityRepository.findAll();
    for (Community community : communities) {
      String key = "viewComm" + community.getId();
      ValueOperations<String, Integer> valueOperations = integerRedisTemplate.opsForValue();
      if (valueOperations.get(key) == null) {
        System.out.println("키가 없네요 , 넘어갈게요");
        community.updateViewCnt(0);
      } else {
        System.out.println("키 있군요! , " + valueOperations.get(key) + " 만큼 변화시킬게요");
        community.updateViewCnt(valueOperations.get(key));
        integerRedisTemplate.delete(key);
      }
      System.out.println(community.getYesterdayView());
      communityRepository.save(community);
    }
    log.info("커뮤니티 조회수 업데이트 완료!");
  }

  @Scheduled(cron = "0 0 3 * * ?") // 새벽 3시마다 업데이트
  @Transactional
  public void schedule() {
    log.info("스케쥴링 : 커뮤니티 업데이트 시작!");
    updateViewCommunity();
    log.info("스케쥴링 : 커뮤니티 업데이트 완료!");
  }

  @Override
  public int getTotalPage() {
    List<Community> communities = communityRepository.findAll();

    return 0;
  }

//  Long getCommunityTotalView(Community community) {
////    Set<String> keys = integerRedisTemplate.keys("Comm*");
//    String pattern = Long.toString(community.getId()) + "Comm*";
//    ScanOptions scanOptions = ScanOptions.scanOptions().match(pattern).build();
//    Long count = 0L;
//    Set<String> keys = integerRedisTemplate.keys(pattern);
//    if (keys != null && !keys.isEmpty()) {
//      count = integerRedisTemplate.countExistingKeys(keys);
//    }
//    count += community.getTotalView() + community.getYesterdayView();
//    return count;
//  }
}
