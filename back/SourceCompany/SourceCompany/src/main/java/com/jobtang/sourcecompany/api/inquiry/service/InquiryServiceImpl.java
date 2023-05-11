package com.jobtang.sourcecompany.api.inquiry.service;

import com.jobtang.sourcecompany.api.exception.CustomException;
import com.jobtang.sourcecompany.api.exception.ErrorCode;
import com.jobtang.sourcecompany.api.inquiry.dto.*;
import com.jobtang.sourcecompany.api.inquiry.entity.Inquiry;
import com.jobtang.sourcecompany.api.inquiry.repository.InquiryRepository;
import com.jobtang.sourcecompany.api.inquiry_comment.dto.GetInquiryCommentResponse;
import com.jobtang.sourcecompany.api.inquiry_comment.entity.InquiryComment;
import com.jobtang.sourcecompany.api.user.entity.User;
import com.jobtang.sourcecompany.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InquiryServiceImpl implements InquiryService {

    private final InquiryRepository inquiryRepository;
    private final UserRepository userRepository;


    @Override
    public List<GetMyInquiryResponse> getMyInquiry(Long userId, Pageable pageable) {
        Page<Inquiry> inquiries = inquiryRepository.findByUserIdAndIsActiveTrue(userId, pageable);
        return inquiries.stream()
                .map(inquiry -> {
                    return GetMyInquiryResponse.EntityToDTO(inquiry);
                }).collect(Collectors.toList());
    }

    @Override
    public GetInquiryResponse getInquiry(Long userId, Long inquiryId) {
        Inquiry inquiry = inquiryRepository.findByIdAndIsActiveTrue(inquiryId)
                .orElseThrow(() -> new CustomException(ErrorCode.INQ_EXISTS));
        List<GetInquiryCommentResponse> getInquiryCommentResponse = new ArrayList<>();
        for (InquiryComment inquiryComment : inquiry.getInquiryComments()){
            if (inquiryComment.isActive()) {
                 getInquiryCommentResponse.add(GetInquiryCommentResponse.EntityToDTO(inquiryComment));
            }
        }
        User user = userRepository.findById(userId).get();
        if (userId != inquiry.getUser().getId() && !user.getRole().equals("ROLE_ADMIN")) {
            throw new CustomException(ErrorCode.INQ_INVALID_USER);
        }
        return GetInquiryResponse.EntityToDTO(inquiry, getInquiryCommentResponse);
    }

    @Override
    public List<GetAllInquiryResponse> getAllInquiry(Pageable pageable) {
        Page<Inquiry> inquiries = inquiryRepository.findAllByIsActiveTrue(pageable);
        return inquiries.stream()
                .map(inquiry -> {
                    String tmp;
                    User user = userRepository.findById(inquiry.getUser().getId()).get();
                    if (inquiry.isLock() == true) {
                        tmp = "비밀글입니다.";
                    }
                    else {
                        tmp = inquiry.getContent();
                    }
                    return GetAllInquiryResponse.builder()
                            .inquiryId(inquiry.getId())
                            .title(tmp)
                            .userName(user.getNickname())
                            .isLock(inquiry.isLock())
                            .createdDate(inquiry.getCreatedDate())
                            .build();

                }).collect(Collectors.toList());
    }

    @Override
    public List<GetAllInquiryResponse> getAllInquiryByUser(Long userId, Pageable pageable) {
        Page<Inquiry> inquiries = inquiryRepository.findAllByIsActiveTrue(pageable);
        return inquiries.stream()
                .map(inquiry -> {
                    String tmp;
                    User user = userRepository.findById(inquiry.getUser().getId()).get();
                    if (inquiry.isLock() == true && user.getId() != userId && user.getRole() != "ROLE_ADMIN") {
                        tmp = "비밀글입니다.";
                    }
                    else {
                        tmp = inquiry.getContent();
                    }
                    return GetAllInquiryResponse.builder()
                            .inquiryId(inquiry.getId())
                            .title(tmp)
                            .userName(user.getNickname())
                            .isLock(inquiry.isLock())
                            .createdDate(inquiry.getCreatedDate())
                            .build();

                }).collect(Collectors.toList());
    }

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

    @Override
    @Transactional
    public void deleteInquiry(Long userId, Long inquiryId) {
        Inquiry inquiry = inquiryRepository
                .findById(inquiryId)
                .orElseThrow(() -> new CustomException(ErrorCode.INQ_EXISTS));
        if (inquiry.getUser().getId() != userId) {
            throw new CustomException(ErrorCode.INQ_INVALID_USER);
        } else if (inquiry.isActive() == false) {
            throw new CustomException(ErrorCode.INQ_EXISTS);
        }
        inquiry.deleteEntity();
    }
}
