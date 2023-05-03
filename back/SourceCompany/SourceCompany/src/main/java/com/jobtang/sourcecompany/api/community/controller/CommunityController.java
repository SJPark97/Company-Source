package com.jobtang.sourcecompany.api.community.controller;


import com.jobtang.sourcecompany.api.community.dto.CreateCommunityRequest;
import com.jobtang.sourcecompany.api.community.service.CommunityService;
import com.jobtang.sourcecompany.api.corp.dto.CorpSearchListDto;
import com.jobtang.sourcecompany.api.user.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/community")
@RequiredArgsConstructor
@Api("게시판 API")
public class CommunityController {
  private  final CommunityService communityService;

  @ApiOperation(
          value = "작성한 기업관련 글을 DB에 넣어주는 API",
          notes = "현재 로그인한 유저 명의로 기업분석 게시글 작성",
          response = CorpSearchListDto.class,
          responseContainer = "List"
  )
  @PostMapping
  public ResponseEntity<?> randCorp(@RequestBody CreateCommunityRequest createCommunityRequest) {
    /*
    jwt token을 통해 User 객체 가져오는 코드로 대체
    User user = token.getLoginedUser();
     */
    User user = null;
    HttpHeaders headers = new HttpHeaders();
    try{
      communityService.createCommunity(user , createCommunityRequest);
    }
    catch (Exception e){
      e.printStackTrace();
    }

    return new ResponseEntity<>( headers, HttpStatus.CREATED);
  }
}
