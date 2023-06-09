package com.jobtang.sourcecompany.api.corp_detail.service;

import com.jobtang.sourcecompany.api.corp_detail.dto.AnalysisResponseDto;

import java.util.HashMap;

public interface AnalysisService {
    AnalysisResponseDto getCorpAnalysis(String analysisId, String corpId, int settingNum);
    String getGptAnalysis(String corpId);
    HashMap getCorpComparison(String corpIdA, String corpIdB);
    // 업데이트
    void updateAnalysisCorp(String variableId);
    void updateAnalysisAllCorp();
    Boolean deleteAnalysisAllCorp();
    void updateAnalysisInfo();
    void updateAnalysisGptAll(int size);
}
