package com.jobtang.sourcecompany.api.inquiry.service;

import com.jobtang.sourcecompany.api.exception.CustomException;
import com.jobtang.sourcecompany.api.exception.ErrorCode;
import com.jobtang.sourcecompany.api.inquiry.dto.CreateInquiryRequest;
import com.jobtang.sourcecompany.api.inquiry.entity.Inquiry;
import com.jobtang.sourcecompany.api.inquiry.repository.InquiryRepository;
import com.jobtang.sourcecompany.api.user.entity.User;
import com.jobtang.sourcecompany.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InquiryServiceImpl implements InquiryService {

    private final InquiryRepository inquiryRepository;
    private final UserRepository userRepository;

    @Override
    public void createInquiry(Long userId, CreateInquiryRequest createInquiryRequest) {
        User user = userRepository.findById(userId).get();
        Inquiry inquiry = Inquiry.builder()
                .title(createInquiryRequest.getTitle())
                .content(createInquiryRequest.getContent())
                .isLock(createInquiryRequest.isLock())
                .user(user)
                .build();
        inquiryRepository.save(inquiry);
    }
}
