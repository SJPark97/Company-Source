package com.jobtang.sourcecompany.api.corp_detail.service;

import com.jobtang.sourcecompany.api.corp_detail.document.AnalysisDocument;
import com.jobtang.sourcecompany.api.corp_detail.document.AnalysisInfoDocument;
import com.jobtang.sourcecompany.api.corp_detail.repository.AnalysisInfoRepository;
import com.jobtang.sourcecompany.api.corp_detail.repository.AnalysisRepository;
import com.jobtang.sourcecompany.api.corp_detail.util.AnalysisInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnalysisServiceImpl implements AnalysisService{

    private final AnalysisRepository analysisRepository;
    private final AnalysisInfoRepository analysisInfoRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public HashMap getCorpAnalysis(String analysisId, String corpId) {
        AnalysisDocument analysisDocument = analysisRepository.findByCorpId(corpId);
        AnalysisInfoDocument analysisInfoDocument = analysisInfoRepository.findByAnalysisId(analysisId);

        HashMap<String, HashMap> data = analysisDocument.getData().get(corpId);

        if (data == null) {log.warn("해당 회사 분석정보가 없습니다" + corpId);return null;}
        String key = "result" + analysisId;
        HashMap result = (HashMap) data.get("analysis").get(key);
        result.put("analysisInfo", analysisInfoDocument.getData());
        return result;
    }

    @Override
    public void updateAnalysisInfo() {
        AnalysisInfo analysisInfo = new AnalysisInfo();
        List<HashMap> dataList = analysisInfo.getInfo();
        mongoTemplate.remove(new Query(), AnalysisInfoDocument.class);

        // MongoDB에 저장하기
        for (HashMap<String, String> data : dataList) {
            AnalysisInfoDocument analysisInfoDocument = new AnalysisInfoDocument();
            String keyName = data.get("analysis_id");
            analysisInfoDocument.setAnalysisId(keyName);
            analysisInfoDocument.setData(data);
            mongoTemplate.save(analysisInfoDocument);
            log.info("분석법 저장완료" + analysisInfo);

        }
        log.info("모든 기업분석 저장완료!");

        return ;
    }
}
