package com.jobtang.sourcecompany.api.comment.controller;

import com.jobtang.sourcecompany.api.community.dto.CreateCommunityRequest;
import com.jobtang.sourcecompany.api.user.entity.User;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
@Api("댓글 API")
public class CommentController {

//  @PostMapping("/corp")
//  public ResponseEntity<?> createCommunity(@RequestBody CreateCommunityRequest createCommunityRequest) {
//    /*
//    jwt token을 통해 User 객체 가져오는 코드로 대체
//    User user = token.getLoginedUser();
//     */
//    User user = null;
//    HttpHeaders headers = new HttpHeaders();
//    try{
//      communityService.createCommunity(user , createCommunityRequest);
//      return new ResponseEntity<>( headers, HttpStatus.CREATED);
//    }
//    catch (Exception e){
//      e.printStackTrace();
//      return new ResponseEntity<>( "fail",headers, HttpStatus.BAD_REQUEST);
//    }
//  }
}
