package com.jobtang.sourcecompany.api.inquiry_comment.service;

import com.jobtang.sourcecompany.api.exception.CustomException;
import com.jobtang.sourcecompany.api.exception.ErrorCode;
import com.jobtang.sourcecompany.api.inquiry.entity.Inquiry;
import com.jobtang.sourcecompany.api.inquiry.repository.InquiryRepository;
import com.jobtang.sourcecompany.api.inquiry_comment.dto.CreateInquiryCommentRequest;
import com.jobtang.sourcecompany.api.inquiry_comment.entity.InquiryComment;
import com.jobtang.sourcecompany.api.inquiry_comment.repository.InquiryCommentRepository;
import com.jobtang.sourcecompany.api.user.entity.User;
import com.jobtang.sourcecompany.api.user.repository.UserRepository;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Service
@RequiredArgsConstructor
public class InquiryCommentServiceImpl implements InquiryCommentService {

    private final InquiryRepository inquiryRepository;
    private final UserRepository userRepository;
    private final InquiryCommentRepository inquiryCommentRepository;

    @Override
    public void createComment(Long userId, CreateInquiryCommentRequest createInquiryCommentRequest) {
        User user = userRepository.findById(userId).get();
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

}
