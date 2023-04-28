package com.jobtang.sourcecompany.api.corp_detail.service;

import com.jobtang.sourcecompany.api.corp_detail.document.AnalysisDocument;
import com.jobtang.sourcecompany.api.corp_detail.repository.AnalysisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnalysisServiceImpl implements AnalysisService{

    private final AnalysisRepository analysisRepository;

    @Override
    public HashMap getCorpAnalysis(int analysisId, String corpId) {
        AnalysisDocument analysisDocument = analysisRepository.findByCorpId(corpId);
        System.out.println(analysisId);
        System.out.println(corpId);

        System.out.println(analysisDocument.toString());

        HashMap<String, HashMap> data = analysisDocument.getData().get(corpId);

        if (data == null) {log.warn("해당 회사 분석정보가 없습니다" + corpId);return null;}

        String key = "result" + Integer.toString(analysisId);

        return (HashMap) data.get("analysis").get(key);
    }
}
