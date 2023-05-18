package com.jobtang.sourcecompany.api.corp_detail.service;

import com.jobtang.sourcecompany.api.corp.dto.CorpListResponseDto;

public interface CorpDetailService {
    CorpListResponseDto getTopSalesCorps(int size, int page);
}
