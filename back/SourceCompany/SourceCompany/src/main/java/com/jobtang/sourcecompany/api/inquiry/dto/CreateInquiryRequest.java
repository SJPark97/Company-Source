package com.jobtang.sourcecompany.api.inquiry.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class CreateInquiryRequest {
    private String title;

    private String content;

    private boolean isLock;
}
