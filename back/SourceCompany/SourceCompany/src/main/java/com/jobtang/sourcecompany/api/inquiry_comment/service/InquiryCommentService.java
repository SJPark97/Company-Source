package com.jobtang.sourcecompany.api.inquiry_comment.service;

import com.jobtang.sourcecompany.api.inquiry_comment.dto.CreateInquiryCommentRequest;
import org.springframework.stereotype.Service;

@Service
public interface InquiryCommentService {

    void createComment(Long userId, CreateInquiryCommentRequest createInquiryCommentRequest);
}
