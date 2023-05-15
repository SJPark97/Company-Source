package com.jobtang.sourcecompany.api.user.controller;

import com.jobtang.sourcecompany.api.community.service.CommunityService;
import com.jobtang.sourcecompany.api.user.dto.LoginRequestDto;
import com.jobtang.sourcecompany.api.user.dto.UserCommunity;
import com.jobtang.sourcecompany.api.user.dto.UserInfo;
import com.jobtang.sourcecompany.api.user.service.JwtService;
import com.jobtang.sourcecompany.api.user.service.MyPageService;
import com.jobtang.sourcecompany.util.ResponseHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/mypage")
@RequiredArgsConstructor
@Api("마이페이지 API")
public class MyPageController {
    private final JwtService jwtService;
    private final MyPageService myPageService;
    private final ResponseHandler responseHandler;

    // 마이페이지 유저 정보 반환
    @ApiOperation(
            value = "유저 정보 반환",
            notes = "유저 정보 반환",
            response = HashMap.class
    )
    @GetMapping(value = "/userinfo")
    public ResponseEntity<?> myPageUserInfo(@RequestHeader("Authorization") String token) {
        Long userId = jwtService.userPkByToken(token);
        UserInfo userInfo = myPageService.getUserInfo(userId);
        return responseHandler.response(userInfo);
    }

    // 마이페이지 유저 게시글 반환
    @ApiOperation(
            value = "유저 게시글 반환",
            notes = "유저 게시글 반환",
            response = HashMap.class
    )
    @GetMapping(value = "/usercommunity")
    public ResponseEntity<?> myPageUserCommunity(@RequestHeader("Authorization") String token) {
        Long userId = jwtService.userPkByToken(token);
        List<UserCommunity> userCommunityList = myPageService.getUserCommunityList(userId);
        return responseHandler.response(userCommunityList);
    }

}
