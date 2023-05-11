package com.jobtang.sourcecompany.api.inquiry_comment.repository;

import com.jobtang.sourcecompany.api.inquiry.entity.Inquiry;
import com.jobtang.sourcecompany.api.inquiry_comment.entity.InquiryComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryCommentRepository extends JpaRepository<InquiryComment, Long> {
}
