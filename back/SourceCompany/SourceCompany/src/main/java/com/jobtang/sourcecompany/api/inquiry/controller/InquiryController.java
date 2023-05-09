package com.jobtang.sourcecompany.api.inquiry.controller;


import com.jobtang.sourcecompany.api.inquiry.dto.CreateInquiryRequest;
import com.jobtang.sourcecompany.api.inquiry.service.InquiryService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("/api/v1/inquiry")
@RequiredArgsConstructor
@Api("문의게시판 API")
public class InquiryController {

    private final InquiryService inquiryService;
    @PostMapping("")
    public ResponseEntity<?> createInquiry(@RequestBody CreateInquiryRequest createInquiryRequest,
                                            @RequestHeader("Authorization") String authHeader) {
        HashMap<String, Object> result = new HashMap<>();
        Long userId = Long.parseLong(authHeader.substring(7));
        inquiryService.createInquiry(userId, createInquiryRequest);
        result.put("data", "success");
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}