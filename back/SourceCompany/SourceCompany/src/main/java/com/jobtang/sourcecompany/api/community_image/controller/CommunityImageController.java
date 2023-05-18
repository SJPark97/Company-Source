package com.jobtang.sourcecompany.api.community_image.controller;

import com.jobtang.sourcecompany.api.community_image.service.CommunityImageService;
import com.jobtang.sourcecompany.api.user.service.JwtService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("/api/v1/saveimg")
@RequiredArgsConstructor
@Api("이미지 저장 API")
public class CommunityImageController {

    private final CommunityImageService communityImageService;
    private final JwtService jwtService;

    @PostMapping("")
    public ResponseEntity<?> createCommunityImage(@RequestParam("file") MultipartFile file,
                                                  @RequestHeader("Authorization") String authHeader) {
        Long userId = jwtService.userPkByToken(authHeader);
        HashMap<String, Object> result = communityImageService.createCommunityImage(file, userId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/1")
    public ResponseEntity<?> createCommunityImage(@RequestParam("file") MultipartFile file) {
        return new ResponseEntity<>("hi", HttpStatus.OK);
    }
}
