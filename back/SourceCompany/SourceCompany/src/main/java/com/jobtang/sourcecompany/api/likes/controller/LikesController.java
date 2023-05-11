package com.jobtang.sourcecompany.api.likes.controller;

import com.jobtang.sourcecompany.api.community.dto.ReadAllCommunityResponse;
import com.jobtang.sourcecompany.api.likes.service.LikesService;
import com.jobtang.sourcecompany.api.user.service.JwtService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/like")
@RequiredArgsConstructor
@Api("좋아요 API")
public class LikesController {
  private  final LikesService likesService;
  private final JwtService jwtService;
  /**
   * 게시글을 로그인한 유저가 좋아요 하는 메소드
   */
  @ApiOperation(
          value = "게시글 좋아요",
          notes = "해당 게시글에 좋아요 등록"
  )
  @PostMapping("/like/{community_id}")
  public ResponseEntity<?>  createLikes(@RequestHeader("Authorization") String token , @PathVariable Long communityId) {

    HttpHeaders headers = new HttpHeaders();
    Long userId = jwtService.userPkByToken(token);
    HashMap<String, Object> result = new HashMap<>();
    Long likesId = likesService.createLikes(communityId , userId);
    result.put("data", likesId);
    return new ResponseEntity<>(result, headers, HttpStatus.OK);
  }

  @ApiOperation(
          value = "게시글 좋아요 삭제",
          notes = "게시글의 좋아요 삭제"
  )
  @DeleteMapping("/like/{community_id}")
  public ResponseEntity<?> deleteLikes( @PathVariable Long likesId) {
    HttpHeaders headers = new HttpHeaders();
    HashMap<String, Object> result = new HashMap<>();
    likesService.deleteLikes(likesId);
    result.put("data", likesId);
    return new ResponseEntity<>(result, headers, HttpStatus.OK);
  }
}
