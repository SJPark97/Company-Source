package com.jobtang.sourcecompany.api.inquiry.controller;


import com.jobtang.sourcecompany.api.inquiry.dto.*;
import com.jobtang.sourcecompany.api.inquiry.service.InquiryService;
import com.jobtang.sourcecompany.api.user.service.JwtService;
import com.jobtang.sourcecompany.config.JwtTokenProvider;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@RestController
@RequestMapping("/api/v1/inquiry")
@RequiredArgsConstructor
@Api("문의게시판 API")
public class InquiryController {

    private final InquiryService inquiryService;
    private final JwtService jwtService;

    @GetMapping("/my")
    public ResponseEntity<?> getMyInquiry(@RequestBody GetAllInquiryRequest getAllInquiryRequest,
                                            @RequestHeader("Authorization") String authHeader) {
        HashMap<String, Object> result = new HashMap<>();
        Pageable pageable = PageRequest.of(getAllInquiryRequest.getPage(), getAllInquiryRequest.getSize());
        Long userId = jwtService.userPkByToken(authHeader);
        List<GetMyInquiryResponse> response = inquiryService.getMyInquiry(userId, pageable);
        result.put("data", response);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{inquiryId}")
    public ResponseEntity<?> getInquiry(@PathVariable Long inquiryId,
                                        @RequestHeader("Authorization") String authHeader) {
        HashMap<String, Object> result = new HashMap<>();
        Long userId = jwtService.userPkByToken(authHeader);
        GetInquiryResponse response = inquiryService.getInquiry(userId, inquiryId);
        result.put("data", response);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/guest")
    public ResponseEntity<?> getAllInquiry(@RequestBody GetAllInquiryRequest getAllInquiryRequest) {
        HashMap<String, Object> result = new HashMap<>();
        Pageable pageable = PageRequest.of(getAllInquiryRequest.getPage(), getAllInquiryRequest.getSize());
        List<GetAllInquiryResponse> response = inquiryService.getAllInquiry(pageable);
        result.put("data", response);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllInquiryByUser(@RequestBody GetAllInquiryRequest getAllInquiryRequest,
                                           @RequestHeader("Authorization") String authHeader) {
        HashMap<String, Object> result = new HashMap<>();
        Pageable pageable = PageRequest.of(getAllInquiryRequest.getPage(), getAllInquiryRequest.getSize());
        Long userId = jwtService.userPkByToken(authHeader);
        List<GetAllInquiryResponse> response = inquiryService.getAllInquiryByUser(userId, pageable);
        result.put("data", response);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createInquiry(@RequestBody CreateInquiryRequest createInquiryRequest,
                                           @RequestHeader("Authorization") String authHeader) {
        HashMap<String, Object> result = new HashMap<>();
        Long userId = jwtService.userPkByToken(authHeader);
        inquiryService.createInquiry(userId, createInquiryRequest);
        result.put("data", "success");
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<? >updateInquiry(@RequestBody UpdateInquiryRequest updateInquiryRequest,
                                           @RequestHeader("Authorization") String authHeader) {
        HashMap<String, Object> result = new HashMap<>();
        Long userId = jwtService.userPkByToken(authHeader);
        result.put("data", inquiryService.updateInquiry(userId, updateInquiryRequest));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{inquiryId}")
    public ResponseEntity<? >deleteInquiry(@PathVariable Long inquiryId,
                                            @RequestHeader("Authorization") String authHeader) {
        Long userId = jwtService.userPkByToken(authHeader);
        inquiryService.deleteInquiry(userId, inquiryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
