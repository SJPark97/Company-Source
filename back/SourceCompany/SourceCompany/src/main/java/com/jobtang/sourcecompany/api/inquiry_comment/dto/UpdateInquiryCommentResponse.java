package com.jobtang.sourcecompany.api.inquiry_comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class UpdateInquiryCommentResponse {
    private String content;
}
