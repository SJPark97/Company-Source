package com.jobtang.sourcecompany.api.corp_detail.service;

import java.util.HashMap;

public interface AnalysisService {
    HashMap getCorpAnalysis(String analysisId, String corpId);
    void updateAnalysisInfo();
}
