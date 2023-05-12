package com.jobtang.sourcecompany.api.inquiry.dto;

import com.jobtang.sourcecompany.api.inquiry.entity.Inquiry;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class GetMyInquiryResponse {
    private Long inquiryId;
    private String title;
    private boolean isLock;
    private LocalDateTime createdDate;

    public static GetMyInquiryResponse EntityToDTO (Inquiry inquiry){
        return GetMyInquiryResponse.builder()
                .inquiryId(inquiry.getId())
                .title(inquiry.getTitle())
                .isLock(inquiry.isLock())
                .createdDate(inquiry.getCreatedDate())
                .build();
    }
}
