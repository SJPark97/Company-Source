package com.jobtang.sourcecompany.api.comment.controller;

import com.jobtang.sourcecompany.api.comment.service.CommentService;
import com.jobtang.sourcecompany.api.community.dto.CreateCommunityRequest;
import com.jobtang.sourcecompany.api.community.dto.UpdateCommunityRequest;
import com.jobtang.sourcecompany.api.user.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
@Api("댓글 API")
public class CommentController {

  final private CommentService commentService;
  /**
   *
   */
  @ApiOperation(
          value = "댓글 생성",
          notes = "게시판에 댓글을 작성하는 API"
  )
  @PostMapping
  public ResponseEntity<?> updateFreeCommunity(@RequestBody UpdateCommunityRequest updateCommunityRequest) {
    HttpHeaders headers = new HttpHeaders();
    HashMap<String, Object> result = new HashMap<>();
//
//    result.put("data", communityService.updateCommunity(updateCommunityRequest));
    return new ResponseEntity<>(result, headers, HttpStatus.OK);
  }



  @ApiOperation(
          value = "댓글 삭제",
          notes = "댓글을 삭제하는 API"
  )
  @DeleteMapping
  public ResponseEntity<?> deleteCommentCommunity(@RequestBody UpdateCommunityRequest updateCommunityRequest) {
    HttpHeaders headers = new HttpHeaders();
    HashMap<String, Object> result = new HashMap<>();
//
//    result.put("data", communityService.updateCommunity(updateCommunityRequest));
    return new ResponseEntity<>(result, headers, HttpStatus.OK);
  }


  @ApiOperation(
          value = "댓글 수정",
          notes = "댓글 내용을 수정하는 API"
  )
  @PutMapping
  public ResponseEntity<?> updateComment(@RequestBody UpdateCommunityRequest updateCommunityRequest) {
    HttpHeaders headers = new HttpHeaders();
    HashMap<String, Object> result = new HashMap<>();
//
//    result.put("data", communityService.updateCommunity(updateCommunityRequest));
    return new ResponseEntity<>(result, headers, HttpStatus.OK);
  }

}
