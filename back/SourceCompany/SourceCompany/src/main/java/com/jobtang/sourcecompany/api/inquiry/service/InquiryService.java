package com.jobtang.sourcecompany.api.inquiry.service;

import com.jobtang.sourcecompany.api.inquiry.dto.CreateInquiryRequest;
import com.jobtang.sourcecompany.api.inquiry.dto.UpdateInquiryRequest;
import com.jobtang.sourcecompany.api.inquiry.dto.UpdateInquiryResponse;
import com.jobtang.sourcecompany.api.user.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface InquiryService {
    void createInquiry(Long userId, CreateInquiryRequest createInquiryRequest);

    UpdateInquiryResponse updateInquiry(Long userId, UpdateInquiryRequest updateInquiryRequest);
}
