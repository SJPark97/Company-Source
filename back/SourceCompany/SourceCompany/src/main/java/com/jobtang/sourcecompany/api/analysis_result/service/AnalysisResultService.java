package com.jobtang.sourcecompany.api.analysis_result.service;

import com.jobtang.sourcecompany.api.corp.dto.CorpListResponseDto;
import com.jobtang.sourcecompany.api.corp.entity.Corp;

public interface AnalysisResultService {
    // 분석 결과 저장
    void updateAnalysisResult(Corp corp, String analysisId, String rate);
    CorpListResponseDto GetGoodCorps(int size, int page);
}
