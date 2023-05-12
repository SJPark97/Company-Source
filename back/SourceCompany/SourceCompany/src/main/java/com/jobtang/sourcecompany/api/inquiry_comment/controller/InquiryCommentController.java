package com.jobtang.sourcecompany.api.inquiry_comment.controller;

import com.jobtang.sourcecompany.api.inquiry_comment.dto.CreateInquiryCommentRequest;
import com.jobtang.sourcecompany.api.inquiry_comment.service.InquiryCommentService;
import com.jobtang.sourcecompany.api.user.service.JwtService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@Transactional(readOnly = true)
@RestController
@RequestMapping("/api/v1/inquiry/comment")
@RequiredArgsConstructor
@Api("문의게시판 댓글 API")
public class InquiryCommentController {

    private final InquiryCommentService inquiryCommentService;
    private final JwtService jwtService;

    @PostMapping("")
    @Transactional
    public ResponseEntity<?> createComment(@RequestBody CreateInquiryCommentRequest createInquiryCommentRequest,
                                           @RequestHeader("Authorization") String authHeader) {
        HashMap<String, Object> result = new HashMap<>();
        Long userId = jwtService.userPkByToken(authHeader);
        inquiryCommentService.createComment(userId, createInquiryCommentRequest);
        result.put("data", "success");
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
