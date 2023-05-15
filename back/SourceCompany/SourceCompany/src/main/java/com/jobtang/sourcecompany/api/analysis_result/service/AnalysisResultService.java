package com.jobtang.sourcecompany.api.analysis_result.service;

import com.jobtang.sourcecompany.api.analysis_result.Dto.GoodCorpResponseDto;
import com.jobtang.sourcecompany.api.corp.entity.Corp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;

public interface AnalysisResultService {
    // 분석 결과 저장
    void updateAnalysisResult(Corp corp, String analysisId, String rate);
    GoodCorpResponseDto GetGoodCorps(int size, int page);


}
