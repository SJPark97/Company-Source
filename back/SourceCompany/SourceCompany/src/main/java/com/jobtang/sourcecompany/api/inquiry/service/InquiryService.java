package com.jobtang.sourcecompany.api.inquiry.service;

import com.jobtang.sourcecompany.api.inquiry.dto.CreateInquiryRequest;
import com.jobtang.sourcecompany.api.user.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface InquiryService {
    void createInquiry(Long userId, CreateInquiryRequest createInquiryRequest) throws Exception;
}
