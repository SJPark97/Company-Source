package com.jobtang.sourcecompany.api.corp_detail.service;

<<<<<<< HEAD
import com.jobtang.sourcecompany.api.corp_detail.dto.AnalysisResponseDto;
import com.jobtang.sourcecompany.api.corp_detail.dto.VariableDto;

import java.util.HashMap;

public interface AnalysisService {
    void updateAnalysisCorp(String variableId);
    void updateAnalysisAllCorp();
    AnalysisResponseDto getCorpAnalysis(String analysisId, String corpId);
    Boolean deleteAnalysisAllCorp();
=======
import java.util.HashMap;

public interface AnalysisService {
    HashMap getCorpAnalysis(String analysisId, String corpId);
>>>>>>> dcd36873a727d1402c37c4c0deafe32f26e4f324
    void updateAnalysisInfo();
}
