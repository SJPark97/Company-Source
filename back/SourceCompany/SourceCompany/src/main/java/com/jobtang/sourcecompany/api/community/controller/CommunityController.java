package com.jobtang.sourcecompany.api.community.controller;


import com.jobtang.sourcecompany.api.community.dto.*;
import com.jobtang.sourcecompany.api.community.service.CommunityService;
import com.jobtang.sourcecompany.api.corp.dto.CorpSearchListDto;
import com.jobtang.sourcecompany.api.exception.CustomException;
import com.jobtang.sourcecompany.api.exception.ErrorCode;
import com.jobtang.sourcecompany.api.user.entity.User;
import com.jobtang.sourcecompany.api.user.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/community")
@RequiredArgsConstructor
@Api("게시판 API")
public class CommunityController {
  private final CommunityService communityService;
  // 테스트 용 유저 가져오기
  private final UserRepository userRepository;

  @ApiOperation(
          value = "기업분석 게시글 작성",
          notes = "현재 로그인한 유저 명의로 기업분석 게시글 작성"
  )
  @PostMapping("/corp")
  public ResponseEntity<?> createCommunity(@RequestBody CreateCommunityRequest createCommunityRequest) throws Exception {
    /*
    jwt token을 통해 User 객체 가져오는 코드로 대체
    User user = token.getLoginedUser();
     */
    User user = userRepository.findById(10L).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));


    HashMap<String, Object> result = new HashMap<>();
    HttpHeaders headers = new HttpHeaders();

    communityService.createCommunity("기업",user, createCommunityRequest);
    result.put("data", "success");
    return new ResponseEntity<>(result, HttpStatus.CREATED);

  }

  /**
   * /community/corp/{community_id} GET
   * 기업 분석 게시판을 상세조회하는 메소드
   * redis에 조회수를 추가해야함
   */
  @ApiOperation(
          value = "기업 분석 게시글 상세조회",
          notes = "해당 게시판의 detail한 정보와 달린 댓글들을 리턴해주고 , 조회수를 늘려주는 메소드"
  )
  @GetMapping("/corp/{communityId}")
  public ResponseEntity<?> findCommunityDetail(@PathVariable Long communityId) {
    HttpHeaders headers = new HttpHeaders();
    HashMap<String, Object> result = new HashMap<>();

    ReadCommunityDetailResponse response = communityService.readCommunityDetail("기업",communityId);
    result.put("data", response);
    return new ResponseEntity<>(result, headers, HttpStatus.OK);

  }
  /**
   * /community/randing GET
   // 랜딩 게시판을 리턴해주는 메소드
   // 기업  / 자유 게시판의  최근 게시글
   // 기업  / 자유 게시판의  어제자 조회수 높은 게시글
   */
  @ApiOperation(
          value = "게시글 랜딩 페이지",
          notes = "기업 및 자유게시판들의 랜딩게시판 조회"

  )
  @GetMapping("/corp/randing")
  public ResponseEntity<?> readRandingCommunity() {
    HttpHeaders headers = new HttpHeaders();
    HashMap<String, Object> result = new HashMap<>();
    ReadRandingCommunityResponse response = communityService.readRandingCommunity();
    result.put("data", response);
    return new ResponseEntity<>(result, headers, HttpStatus.OK);
  }

  /**
   * /community/corp/search?type={type}&content={content} GET
   * 기업 분석 게시판의 글들을 검색하는 메소드
   * is_active ==0 인 것들은 거르는 것 필요
   * type == (글쓴이, 내용 ,제목) 으로 거르기
   */
  @ApiOperation(
          value = "기업 분석 게시글 전체조회",
          notes = "기업 분석 게시글들을 전체 조회. 페이지와 한페이지 안의 사이즈 요청"

  )
  @GetMapping("/corp/search")
  public ResponseEntity<?> searchCommunity(@RequestParam String content, @RequestParam String type, Pageable pageable) {
    HttpHeaders headers = new HttpHeaders();
    HashMap<String, Object> result = new HashMap<>();
    List<ReadAllCommunityResponse> response = communityService.searchCommunity(content,type,pageable);
    result.put("data", response);
    return new ResponseEntity<>(result, headers, HttpStatus.OK);
  }


  /**
   * /community/corp GET
   * 기업 분석 게시판을 전체조회하는 메소드
   */
  @ApiOperation(
          value = "기업 분석 게시글 전체조회",
          notes = "기업 분석 게시글들을 전체 조회. 페이지와 한페이지 안의 사이즈 요청"

  )
  @GetMapping("/corp")
  public ResponseEntity<?> findAllCommunity(Pageable pageable) {
    HttpHeaders headers = new HttpHeaders();
    HashMap<String, Object> result = new HashMap<>();
    System.out.println(pageable);
    List<ReadAllCommunityResponse> response = communityService.readAllCommunity(pageable);
    result.put("data", response);
    return new ResponseEntity<>(result, headers, HttpStatus.OK);
  }


  /**
   * /community/corp/{communityId} PUT
   * 기업 분석 게시판을 수정하는 메소드
   */
  @ApiOperation(
          value = "기업 분석 게시글 수정",
          notes = "해당 게시판 메소드"
  )
  @PutMapping("/corp")
  public ResponseEntity<?> updateCommunity(@RequestBody UpdateCommunityRequest updateCommunityRequest) {
    HttpHeaders headers = new HttpHeaders();
    HashMap<String, Object> result = new HashMap<>();

    result.put("data", communityService.updateCommunity(updateCommunityRequest));
    return new ResponseEntity<>(result, headers, HttpStatus.OK);

  }

  /**
   * /community/corp/{communityId} DELETE
   * 기업 분석 게시판을 삭제하는 메소드
   */
  @ApiOperation(
          value = "기업 분석 게시글 삭제",
          notes = "해당 게시판의 detail한 정보와 달린 댓글들을 리턴해주고 , 조회수를 늘려주는 메소드"
  )
  @DeleteMapping("/corp/{communityId}")
  public ResponseEntity<?> removeCommunity(@PathVariable Long communityId) {
    HttpHeaders headers = new HttpHeaders();
    HashMap<String, Object> result = new HashMap<>();

    communityService.deleteCommunity(communityId);
    result.put("data", "success");
    return new ResponseEntity<>(result, headers, HttpStatus.OK);

  }


}
