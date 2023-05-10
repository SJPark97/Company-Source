package com.jobtang.sourcecompany.api.corp_detail.service;

import com.jobtang.sourcecompany.api.corp_detail.dto.AnalysisResponseDto;
import com.jobtang.sourcecompany.api.corp_detail.dto.VariableDto;

import java.util.HashMap;

public interface AnalysisService {
    void updateAnalysisCorp(String variableId);
    void updateAnalysisAllCorp();
    AnalysisResponseDto getCorpAnalysis(String analysisId, String corpId, int settingNum);
    Boolean deleteAnalysisAllCorp();
    void updateAnalysisInfo();
    void updateAnalysisGpt(String corpId);
}
