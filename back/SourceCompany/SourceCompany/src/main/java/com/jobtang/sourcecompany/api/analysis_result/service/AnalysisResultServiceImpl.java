package com.jobtang.sourcecompany.api.analysis_result.service;

import com.jobtang.sourcecompany.api.analysis_result.entity.AnalysisResult;
import com.jobtang.sourcecompany.api.analysis_result.repository.AnalysisResultRepository;
import com.jobtang.sourcecompany.api.corp.entity.Corp;
import com.jobtang.sourcecompany.api.corp.repository.CorpRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnalysisResultServiceImpl implements AnalysisResultService{

    private final AnalysisResultRepository analysisResultRepository;
    private final CorpRepository corpRepository;

    @Override
    public void updateAnalysisResult(Corp corp, String analysisId, String rate) {
        AnalysisResult analysisResult = analysisResultRepository.findByCorp(corp);
        if (analysisResult == null) {
            analysisResult = new AnalysisResult();
            analysisResult.setCorp(corpRepository.findByCorpId(corp.getCorpId()));
        }
        analysisResult.updateResult(analysisId, rate);
        analysisResultRepository.save(analysisResult);
    }
}
