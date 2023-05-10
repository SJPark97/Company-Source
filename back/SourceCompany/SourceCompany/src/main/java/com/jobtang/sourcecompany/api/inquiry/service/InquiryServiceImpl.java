package com.jobtang.sourcecompany.api.inquiry.service;

import com.jobtang.sourcecompany.api.exception.CustomException;
import com.jobtang.sourcecompany.api.exception.ErrorCode;
import com.jobtang.sourcecompany.api.inquiry.dto.CreateInquiryRequest;
import com.jobtang.sourcecompany.api.inquiry.dto.UpdateInquiryRequest;
import com.jobtang.sourcecompany.api.inquiry.dto.UpdateInquiryResponse;
import com.jobtang.sourcecompany.api.inquiry.entity.Inquiry;
import com.jobtang.sourcecompany.api.inquiry.repository.InquiryRepository;
import com.jobtang.sourcecompany.api.user.entity.User;
import com.jobtang.sourcecompany.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

    @Override
    @Transactional
    public UpdateInquiryResponse updateInquiry(Long userId, UpdateInquiryRequest updateInquiryRequest) {
        Inquiry inquiry = inquiryRepository
                .findById(updateInquiryRequest.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.INQ_EXISTS));
        if (inquiry.getUser().getId() != userId) {
            throw new CustomException(ErrorCode.INQ_INVALID_USER);
        }
        inquiry.setTitle(updateInquiryRequest.getTitle());
        inquiry.setContent(updateInquiryRequest.getContent());
        inquiry.setIsLock(updateInquiryRequest.isLock());
        return UpdateInquiryResponse.builder()
                .id(inquiry.getId())
                .title(inquiry.getTitle())
                .content(inquiry.getContent())
                .isLock(inquiry.isLock())
                .build();
    }
}
