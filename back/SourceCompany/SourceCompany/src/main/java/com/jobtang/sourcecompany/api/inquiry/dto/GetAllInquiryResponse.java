package com.jobtang.sourcecompany.api.inquiry.dto;

import com.jobtang.sourcecompany.api.community.dto.ReadAllCommunityResponse;
import com.jobtang.sourcecompany.api.community.entity.Community;
import com.jobtang.sourcecompany.api.inquiry.entity.Inquiry;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class GetAllInquiryResponse {
    private Long inquiryId;
    private String title;
    private String userName;
    private boolean isLock;
    private LocalDateTime createdDate;

    public static GetAllInquiryResponse EntityToDTO (Inquiry inquiry){
        return GetAllInquiryResponse.builder()
                .inquiryId(inquiry.getId())
                .title(inquiry.getTitle())
                .userName(inquiry.getUser().getNickname())
                .isLock(inquiry.isLock())
                .createdDate(inquiry.getCreatedDate())
                .build();
    }
}
