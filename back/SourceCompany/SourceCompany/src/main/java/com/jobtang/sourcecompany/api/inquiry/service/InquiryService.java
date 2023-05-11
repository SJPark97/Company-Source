package com.jobtang.sourcecompany.api.inquiry.service;

import com.jobtang.sourcecompany.api.inquiry.dto.*;
import com.jobtang.sourcecompany.api.user.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InquiryService {
    void createInquiry(Long userId, CreateInquiryRequest createInquiryRequest);

    UpdateInquiryResponse updateInquiry(Long userId, UpdateInquiryRequest updateInquiryRequest);

    void deleteInquiry(Long userId, Long inquiryId);

    GetInquiryResponse getInquiry(Long userId, Long inquiryId);

    List<GetMyInquiryResponse> getMyInquiry(Long userId, Pageable pageable);

    List<GetAllInquiryResponse> getAllInquiry(Pageable pageable);

    List<GetAllInquiryResponse> getAllInquiryByUser(Long userId, Pageable pageable);
}
