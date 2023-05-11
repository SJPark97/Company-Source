package com.jobtang.sourcecompany.api.inquiry.repository;

import com.jobtang.sourcecompany.api.community.entity.Community;
import com.jobtang.sourcecompany.api.inquiry.entity.Inquiry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
    Page<Inquiry> findAllByIsActiveTrue (Pageable pageable);

    Optional<Inquiry> findByIdAndIsActiveTrue(Long inquiryId);

    Page<Inquiry> findByUserIdAndIsActiveTrue(Long userId, Pageable pageable);
}
