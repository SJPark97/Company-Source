package com.jobtang.sourcecompany.api.inquiry_comment.dto;

import com.jobtang.sourcecompany.api.inquiry_comment.entity.InquiryComment;
import com.jobtang.sourcecompany.api.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GetInquiryCommentResponse {
    private String content;
    private String userNickname;
    public static GetInquiryCommentResponse EntityToDTO (InquiryComment inquiryComment) {
        return GetInquiryCommentResponse.builder()
                .content(inquiryComment.getContent())
                .userNickname(inquiryComment.getUser().getNickname())
                .build();

    }
}
