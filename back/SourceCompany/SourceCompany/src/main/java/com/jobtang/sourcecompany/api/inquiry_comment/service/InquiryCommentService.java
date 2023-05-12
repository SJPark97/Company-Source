package com.jobtang.sourcecompany.api.inquiry_comment.service;

import com.jobtang.sourcecompany.api.inquiry_comment.dto.CreateInquiryCommentRequest;
import com.jobtang.sourcecompany.api.inquiry_comment.dto.UpdateInquiryCommentRequest;
import com.jobtang.sourcecompany.api.inquiry_comment.dto.UpdateInquiryCommentResponse;
import org.springframework.stereotype.Service;

@Service
public interface InquiryCommentService {

    void createInquiryComment(Long userId, CreateInquiryCommentRequest createInquiryCommentRequest);

    void deleteInquiryComment(Long userId, Long inquiryCommentId);

    UpdateInquiryCommentResponse updateInquiryComment(Long userId, Long inquiryCommentId, UpdateInquiryCommentRequest updateInquiryCommentRequest);
}
