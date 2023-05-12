package com.jobtang.sourcecompany.api.inquiry_comment.repository;

import com.jobtang.sourcecompany.api.inquiry.entity.Inquiry;
import com.jobtang.sourcecompany.api.inquiry_comment.entity.InquiryComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InquiryCommentRepository extends JpaRepository<InquiryComment, Long> {
    Optional<InquiryComment> findByIdAndIsActiveTrue(Long inquiryCommentId);
}
