package com.jobtang.sourcecompany.api.inquiry.dto;

import com.jobtang.sourcecompany.api.inquiry.entity.Inquiry;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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

    public static GetInquiryResponse EntityToDTO (Inquiry inquiry){
        return GetInquiryResponse.builder()
                .inquiryId(inquiry.getId())
                .title(inquiry.getTitle())
                .content(inquiry.getContent())
                .userName(inquiry.getUser().getNickname())
                .isLock(inquiry.isLock())
                .createdDate(inquiry.getCreatedDate())
                .build();
    }
}
