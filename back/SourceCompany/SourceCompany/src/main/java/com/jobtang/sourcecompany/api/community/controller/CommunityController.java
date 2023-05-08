package com.jobtang.sourcecompany.api.community.controller;


import com.jobtang.sourcecompany.api.community.dto.CreateCommunityRequest;
import com.jobtang.sourcecompany.api.community.dto.ReadAllCommunityResponse;
import com.jobtang.sourcecompany.api.community.dto.ReadCommunityDetailResponse;
import com.jobtang.sourcecompany.api.community.dto.UpdateCommunityRequest;
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

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/community")
@RequiredArgsConstructor
@Api("게시판 API")
public class CommunityController {
  private  final CommunityService communityService;
  // 테스트 용 유저 가져오기
  private final UserRepository userRepository;
  @ApiOperation(
          value = "기업분석 게시글 작성",
          notes = "현재 로그인한 유저 명의로 기업분석 게시글 작성"
  )
  @PostMapping("/corp")
  public ResponseEntity<?> createCommunity(@RequestBody CreateCommunityRequest createCommunityRequest) {
    /*
    jwt token을 통해 User 객체 가져오는 코드로 대체
    User user = token.getLoginedUser();
     */
    User user = userRepository.findById(10L).orElseThrow(()->new CustomException(ErrorCode.USER_NOT_FOUND));

    HttpHeaders headers = new HttpHeaders();
    try{
      communityService.createCommunity(user , createCommunityRequest);
    return new ResponseEntity<>( headers, HttpStatus.CREATED);
    }
    catch (Exception e){
      e.printStackTrace();
      return new ResponseEntity<>( "fail",headers, HttpStatus.BAD_REQUEST);
    }
  }

  /**
   * /community/corp/{community_id} GET
   *  기업 분석 게시판을 상세조회하는 메소드
   *  redis에 조회수를 추가해야함
   */
  @ApiOperation(
          value = "기업 분석 게시글 상세조회",
          notes = "해당 게시판의 detail한 정보와 달린 댓글들을 리턴해주고 , 조회수를 늘려주는 메소드"
  )
  @GetMapping("/corp/{communityId}")
  public ResponseEntity<?>  findCommunity (@PathVariable Long communityId) {
    HttpHeaders headers = new HttpHeaders();
    try{
      ReadCommunityDetailResponse response =  communityService.readCommunityDetail(communityId);
      return new ResponseEntity<>( response,  headers, HttpStatus.OK);
    }
    catch (Exception e){
      e.printStackTrace();
      return new ResponseEntity<>( "fail",headers, HttpStatus.BAD_REQUEST);
    }
  }
  /**
   * /community/randing GET
   // 랜딩 게시판을 리턴해주는 메소드
   */


  /**
   * /community/corp/search?type={type}&content={content} GET
   *  기업 분석 게시판의 글들을 검색하는 메소드
   *  is_active ==0 인 것들은 거르는 것 필요
   *  type == (글쓴이, 내용 ,제목) 으로 거르기
   */

  /**
   * /community/corp GET
   *  기업 분석 게시판을 전체조회하는 메소드
   */
  @ApiOperation(
          value = "기업 분석 게시글 전체조회",
          notes = "기업 분석 게시글들을 전체 조회. 페이지와 한페이지 안의 사이즈 요청"

  )
  @GetMapping("/corp")
  public ResponseEntity<?>  findAllCommunity (Pageable pageable) {
    HttpHeaders headers = new HttpHeaders();
    System.out.println(pageable);
    try{
      List<ReadAllCommunityResponse> response =  communityService.readAllCommunity(pageable);
      return new ResponseEntity<>( response,  headers, HttpStatus.OK);
    }
    catch (Exception e){
      e.printStackTrace();
      return new ResponseEntity<>( "fail",headers, HttpStatus.BAD_REQUEST);
    }
  }


  /**
   * /community/corp/{communityId} PUT
   *  기업 분석 게시판을 수정하는 메소드
   */
  @ApiOperation(
          value = "기업 분석 게시글 수정",
          notes = "해당 게시판 메소드"
  )
  @PutMapping("/corp")
  public ResponseEntity<?>  updateCommunity (@RequestBody UpdateCommunityRequest updateCommunityRequest ) {
    HttpHeaders headers = new HttpHeaders();
    try{

      communityService.updateCommunity(updateCommunityRequest );
      return new ResponseEntity<>(  headers, HttpStatus.OK);
    }
    catch (Exception e){
      e.printStackTrace();
      return new ResponseEntity<>( "fail",headers, HttpStatus.BAD_REQUEST);
    }
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
  public ResponseEntity<?>  removeCommunity (@PathVariable Long communityId) {
    HttpHeaders headers = new HttpHeaders();
    try{
      communityService.deleteCommunity(communityId);
      return new ResponseEntity<>(  headers, HttpStatus.OK);
    }
    catch (Exception e){
      e.printStackTrace();
      return new ResponseEntity<>( "fail",headers, HttpStatus.BAD_REQUEST);
    }
  }





}
