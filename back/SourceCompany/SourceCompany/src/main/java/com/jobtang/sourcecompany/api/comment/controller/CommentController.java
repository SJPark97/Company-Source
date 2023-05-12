package com.jobtang.sourcecompany.api.comment.controller;

import com.jobtang.sourcecompany.api.comment.dto.CreateCommentRequest;
import com.jobtang.sourcecompany.api.comment.dto.UpdateCommentRequest;
import com.jobtang.sourcecompany.api.comment.service.CommentService;
import com.jobtang.sourcecompany.api.community.dto.CreateCommunityRequest;
import com.jobtang.sourcecompany.api.community.dto.UpdateCommunityRequest;
import com.jobtang.sourcecompany.api.user.entity.User;
import com.jobtang.sourcecompany.api.user.service.JwtService;
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
  final private JwtService jwtService;

  /**
   *
   */
  @ApiOperation(
          value = "댓글 생성",
          notes = "게시판에 댓글을 작성하는 API"
  )
  @PostMapping
  public ResponseEntity<?> createComment(@RequestHeader("Authorization") String token , @RequestBody CreateCommentRequest createCommentRequest) {
    HttpHeaders headers = new HttpHeaders();
    Long userId= jwtService.userPkByToken(token);
    HashMap<String, Object> result = new HashMap<>();
    Long commentId = commentService.createComment(userId , createCommentRequest);
    result.put("data", commentId);
    return new ResponseEntity<>(result, headers, HttpStatus.OK);
  }



  @ApiOperation(
          value = "댓글 삭제",
          notes = "댓글을 삭제하는 API"
  )
  @DeleteMapping("/{commentId}")
  public ResponseEntity<?> deleteCommentCommunity(@PathVariable Long commentId) {
    HttpHeaders headers = new HttpHeaders();
    HashMap<String, Object> result = new HashMap<>();
    commentService.deleteComment(commentId);
    return new ResponseEntity<>(result, headers, HttpStatus.OK);
  }


  @ApiOperation(
          value = "댓글 수정",
          notes = "댓글 내용을 수정하는 API"
  )
  @PutMapping
  public ResponseEntity<?> updateComment(@RequestBody UpdateCommentRequest updateCommentRequest) {
    HttpHeaders headers = new HttpHeaders();
    HashMap<String, Object> result = new HashMap<>();
        result.put("data", commentService.updateCommunity(updateCommentRequest));
    return new ResponseEntity<>(result, headers, HttpStatus.OK);
  }

}
