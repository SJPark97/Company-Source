package com.jobtang.sourcecompany.api.inquiry_comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateInquiryCommentRequest {
    private Long inquiryId;
    private String content;
}
