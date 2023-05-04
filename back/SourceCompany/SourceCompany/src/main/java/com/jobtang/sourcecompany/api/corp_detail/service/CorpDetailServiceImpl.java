package com.jobtang.sourcecompany.api.corp_detail.service;

import com.jobtang.sourcecompany.api.corp.entity.Corp;
import com.jobtang.sourcecompany.api.corp.repository.CorpRepository;
import com.jobtang.sourcecompany.api.corp_detail.document.AnalysisDocument;
import com.jobtang.sourcecompany.api.corp_detail.document.AnalysisInfoDocument;
import com.jobtang.sourcecompany.api.corp_detail.entity.CorpDetail;
import com.jobtang.sourcecompany.api.corp_detail.repository.AnalysisInfoRepository;
import com.jobtang.sourcecompany.api.corp_detail.repository.AnalysisRepository;
import com.jobtang.sourcecompany.api.corp_detail.repository.CorpDetailRepository;
import com.jobtang.sourcecompany.api.corp_detail.util.Analysis100;
import com.jobtang.sourcecompany.api.corp_detail.util.Analysis400;
import com.jobtang.sourcecompany.api.corp_detail.util.BasicSetting;
import com.jobtang.sourcecompany.api.corp_detail.util.CorpVariable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CorpDetailServiceImpl implements CorpDetailService{

    private final CorpRepository corpRepository;
    private final CorpDetailRepository corpDetailRepository;
    private final MongoTemplate mongoTemplate;

    private final Analysis100 analysis100;
    private final Analysis400 analysis400;
    private final BasicSetting basicSetting;

    @Override
    public void updateCorpDetails(Corp corp) {
        String corpId = corp.getCorpId();
        // 사용할 기업의 정보 가져오기
        CorpDetail corpDetail = corpDetailRepository.findByCorpDetailId(corp.getCorpId());
        if (corpDetail == null) {log.warn("기업의 재무재표가 존재하지 않아 패스합니다 "+ corp.getCorpName());}

        // 종합할 변수 선언
        HashMap data = new HashMap();

        // 분석변수 생성하기
        CorpVariable corpVariable = new CorpVariable(corpDetail.getCorp());

        // 분석법 별로 계산하기
        //100
        data.put("result101", analysis100.analysis101(corpVariable));
        data.put("result103", analysis100.analysis103(corpVariable));
        data.put("result104", analysis100.analysis104(corpVariable));
//        data.put("result106", analysis100.analysis106(corpVariable));
//        data.put("result108", analysis100.analysis108(corpVariable));
        data.put("result109", analysis100.analysis109(corpVariable));
        data.put("result110", analysis100.analysis110(corpVariable));
        data.put("result111", analysis100.analysis111(corpVariable));
        data.put("result113", analysis100.analysis113(corpVariable));

        //400
        data.put("result405", analysis400.analysis405(corpVariable));


        // 기본 세팅 잡기
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        System.out.println(data.toString());
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        HashMap result = basicSetting.basicSettings(corpDetail.getCorp(), data);
        log.info("몽고DB 저장시도");

        // MongoDB에 저장하기
        AnalysisDocument analysisDocument = new AnalysisDocument();
        analysisDocument.setCorpId(corpId);
        analysisDocument.setData(result);
        mongoTemplate.save(analysisDocument);
        log.info(result.toString());
        log.info("기업분석 저장완료! : "+corpDetail.getCorp().getCorpName());
    }

    @Override
    public void updateCorp() {
        deleteCorp();

        List<Corp> corps = corpRepository.findAll();
        for (Corp corp : corps) {
            log.info("기업 분석 진입 : " + corp.getCorpName());
            try {
                updateCorpDetails(corp);
                log.info("성공기업 : " + corp.getCorpName());

            } catch (Exception e) {}

//            updateCorpDetails(corp);
//            log.info("성공기업 : " + corp.getCorpName());

        }
    }

    @Override
    public void deleteCorp() {
        mongoTemplate.remove(new Query(), AnalysisDocument.class);
        log.info("기존 데이터 삭제 완료!");
    }

    // 테스트용
    @Override
    public void updateOne(String corpId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("corpId").is(corpId));
        mongoTemplate.remove(query, AnalysisDocument.class);
        log.info("기존 정보 삭제 완료!");

        Corp corp = corpRepository.findByCorpId(corpId);
        try {
            updateCorpDetails(corp);
            log.info("성공기업 : " + corp.getCorpName());

        } catch (Exception e) {log.warn("실패기업 : " + corp.getCorpName());}

    }

}
