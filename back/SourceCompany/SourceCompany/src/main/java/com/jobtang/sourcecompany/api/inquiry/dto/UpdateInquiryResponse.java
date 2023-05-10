package com.jobtang.sourcecompany.api.inquiry.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
public class UpdateInquiryResponse {
    private Long id;

    private String title;

    private String content;

    private boolean isLock;
}
