package com.jobtang.sourcecompany.api.inquiry.repository;

import com.jobtang.sourcecompany.api.inquiry.entity.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
}
