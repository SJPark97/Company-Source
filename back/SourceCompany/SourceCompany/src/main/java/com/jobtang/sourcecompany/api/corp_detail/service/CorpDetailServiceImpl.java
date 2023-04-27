package com.jobtang.sourcecompany.api.corp_detail.service;

import com.jobtang.sourcecompany.api.corp.entity.Corp;
import com.jobtang.sourcecompany.api.corp_detail.document.AnalysisDocument;
import com.jobtang.sourcecompany.api.corp_detail.entity.CorpDetail;
import com.jobtang.sourcecompany.api.corp_detail.repository.CorpDetailRepository;
import com.jobtang.sourcecompany.api.corp_detail.util.Analysis100;
import com.jobtang.sourcecompany.api.corp_detail.util.BasicSetting;
import com.jobtang.sourcecompany.api.corp_detail.util.Calculator;
import com.jobtang.sourcecompany.api.corp_detail.util.CorpVariable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CorpDetailServiceImpl implements CorpDetailService{

    private final CorpDetailRepository corpDetailRepository;
    private final MongoTemplate mongoTemplate;

    private final Analysis100 analysis100;
    private final BasicSetting basicSetting;

    @Override
    public void updateCorpDetails(String corpId) {
        // 사용할 기업의 정보 가져오기
        CorpDetail corpDetail = corpDetailRepository.findByCorpDetailId(corpId);
        if (corpDetail == null) {log.warn("기업의 재무재표가 존재하지 않아 패스합니다 "+corpId);}

        // 분석변수 생성하기
        CorpVariable corpVariable = new CorpVariable(corpDetail.getCorp());

        // 분석법 별로 계산하기기
        HashMap result101 = analysis100.analysis101(corpVariable);




        // 기본 세팅 잡기
        HashMap data = new HashMap();
        HashMap result = basicSetting.basicSettings(corpDetail.getCorp(), data);

        // MongoDB에 저장하기
        AnalysisDocument analysisDocument = new AnalysisDocument();
        analysisDocument.setCorpId(corpId);
        analysisDocument.setData(result);
        mongoTemplate.save(analysisDocument);
        log.info("기업분석 저장완료! : "+corpDetail.getCorp().getCorpName());
    }







}
