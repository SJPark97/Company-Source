package com.jobtang.sourcecompany.api.inquiry_comment.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Transactional(readOnly = true)
@RestController
@RequestMapping("/api/v1/inquiry")
@RequiredArgsConstructor
@Api("문의게시판 API")
public class InquiryCommentController {
}
