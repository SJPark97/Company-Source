package com.jobtang.sourcecompany.api.inquiry.service;

import com.jobtang.sourcecompany.api.exception.CustomException;
import com.jobtang.sourcecompany.api.exception.ErrorCode;
import com.jobtang.sourcecompany.api.inquiry.dto.CreateInquiryRequest;
import com.jobtang.sourcecompany.api.inquiry.entity.Inquiry;
import com.jobtang.sourcecompany.api.inquiry.repository.InquiryRepository;
import com.jobtang.sourcecompany.api.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InquiryServiceImpl implements InquiryService {

    private final InquiryRepository inquiryRepository;

    @Override
    public void createInquiry(Long userId, CreateInquiryRequest createInquiryRequest) {
        if (userId == null) {
            throw new CustomException(ErrorCode.);
        }
    }
}
