package com.jobtang.sourcecompany.api.inquiry.dto;

import com.jobtang.sourcecompany.api.inquiry.entity.Inquiry;
import com.jobtang.sourcecompany.api.inquiry_comment.dto.GetInquiryCommentResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class GetInquiryResponse {
    private Long inquiryId;
    private String title;

    private String content;
    private String userName;
    private boolean isLock;
    private LocalDateTime createdDate;
    List<GetInquiryCommentResponse> inquiryComments;

    public static GetInquiryResponse EntityToDTO (Inquiry inquiry, List<GetInquiryCommentResponse> inquiryComments){
        return GetInquiryResponse.builder()
                .inquiryId(inquiry.getId())
                .title(inquiry.getTitle())
                .content(inquiry.getContent())
                .inquiryComments(inquiryComments)
                .userName(inquiry.getUser().getNickname())
                .isLock(inquiry.isLock())
                .createdDate(inquiry.getCreatedDate())
                .build();
    }
}
