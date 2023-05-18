package com.jobtang.sourcecompany.api.inquiry_comment.service;

import com.jobtang.sourcecompany.api.exception.customerror.CustomException;
import com.jobtang.sourcecompany.api.exception.customerror.ErrorCode;
import com.jobtang.sourcecompany.api.inquiry.entity.Inquiry;
import com.jobtang.sourcecompany.api.inquiry.repository.InquiryRepository;
import com.jobtang.sourcecompany.api.inquiry_comment.dto.CreateInquiryCommentRequest;
import com.jobtang.sourcecompany.api.inquiry_comment.dto.UpdateInquiryCommentRequest;
import com.jobtang.sourcecompany.api.inquiry_comment.dto.UpdateInquiryCommentResponse;
import com.jobtang.sourcecompany.api.inquiry_comment.entity.InquiryComment;
import com.jobtang.sourcecompany.api.inquiry_comment.repository.InquiryCommentRepository;
import com.jobtang.sourcecompany.api.user.entity.User;
import com.jobtang.sourcecompany.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class InquiryCommentServiceImpl implements InquiryCommentService {

    private final InquiryRepository inquiryRepository;
    private final UserRepository userRepository;
    private final InquiryCommentRepository inquiryCommentRepository;

    @Override
    @Transactional
    public void createInquiryComment(Long userId, CreateInquiryCommentRequest createInquiryCommentRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(ErrorCode.USER_EXISTS));
        if (!user.getRole().equals("ROLE_ADMIN")) {
            throw new CustomException(ErrorCode.INQ_INVALID_USER);
        }
        Inquiry inquiry = inquiryRepository.findById(createInquiryCommentRequest.getInquiryId())
                .orElseThrow(() -> new CustomException(ErrorCode.INQ_EXISTS));

        InquiryComment inquiryComment = InquiryComment.builder()
                .content(createInquiryCommentRequest.getContent())
                .inquiry(inquiry)
                .user(user)
                .build();
        inquiryCommentRepository.save(inquiryComment);
    }

    @Override
    @Transactional
    public void deleteInquiryComment(Long userId, Long inquiryCommentId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(ErrorCode.USER_EXISTS));
        InquiryComment inquiryComment = inquiryCommentRepository.findByIdAndIsActiveTrue(inquiryCommentId)
                .orElseThrow(() -> new CustomException(ErrorCode.INQ_COMM_EXISTS));
        if (user.getId() != inquiryComment.getUser().getId()) {
            throw new CustomException(ErrorCode.INQ_INVALID_USER);
        }
        inquiryComment.deleteEntity();
    }

    @Override
    @Transactional
    public UpdateInquiryCommentResponse updateInquiryComment(Long userId, Long inquiryCommentId, UpdateInquiryCommentRequest updateInquiryCommentRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(ErrorCode.USER_EXISTS));
        InquiryComment inquiryComment = inquiryCommentRepository.findByIdAndIsActiveTrue(inquiryCommentId)
                .orElseThrow(() -> new CustomException(ErrorCode.INQ_COMM_EXISTS));
        if (user.getId() != inquiryComment.getUser().getId()) {
            throw new CustomException(ErrorCode.INQ_INVALID_USER);
        }
        inquiryComment.setContent(updateInquiryCommentRequest.getContent());
        return UpdateInquiryCommentResponse.builder()
                .content(updateInquiryCommentRequest.getContent())
                .build();
    }

}
