package com.jobtang.sourcecompany.api.inquiry.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetAllInquiryRequest {
    private Integer page;

    private Integer size;
}
