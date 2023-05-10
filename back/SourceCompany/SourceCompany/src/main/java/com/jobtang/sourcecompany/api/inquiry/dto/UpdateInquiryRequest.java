package com.jobtang.sourcecompany.api.inquiry.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
public class UpdateInquiryRequest {
        private Long id;

        private String title;

        private String content;

        private boolean isLock;
}
