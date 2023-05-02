package com.jobtang.sourcecompany.api.corp_detail.util;

import com.jobtang.sourcecompany.api.induty_detail.entity.IndutyDetail;
import com.jobtang.sourcecompany.api.induty_detail.repository.IndutyDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class Analysis400 {

    private final IndutyDetailRepository indutyDetailRepository;

    // Z-Score 분석 405
    public HashMap analysis405(CorpVariable target) {
        // 기본 변수 설정
        String analysisName = "Z-Score";
        int analysisNum = 405;
        Boolean isExistAll = true;
        List data = new ArrayList();
        int goodCount = 0;

        log.info(analysisName+"("+String.valueOf(analysisNum)+") 진입");

        // 산업 설정
        IndutyDetail indutyDetail = indutyDetailRepository.findByIndutyCode(target.corp.getIndutyCode());
        if (indutyDetail == null) {
            log.warn("해당 산업의 정보가 없습니다" + target.corp.getIndutyName());
            isExistAll = false;
        }

        // 타겟의 빈값이 있으면
        if (
                target.netWorkingCapital == null ||  // 순운전자본
                target.corpDetail.getTotalAssets() == null ||  // 총자산
                target.corpDetail.getRetainedEarnings() == null ||  // 이익잉여금
                target.corpDetail.getNetProfit() == null ||  // 순이익 +
                target.corpDetail.getTax() == null ||  // 세금
                target.corpDetail.getInterestExpense() == null || // 이지비용
                target.corpDetail.getEquityCapital() == null ||  // 자기자본 시장가치
                target.corpDetail.getSales() == null // 매출액

        ) {isExistAll = false;}

        // 산업의 빈값이 있으면
        IndutyVariable indutyVariable = new IndutyVariable(indutyDetail);
        if (
                indutyVariable.netWorkingCapital == null ||  // 순운전자본
                indutyVariable.indutyDetail.getTotalAssets() == null ||  // 총자산
                indutyVariable.indutyDetail.getRetainedEarnings() == null ||  // 이익잉여금
                indutyVariable.indutyDetail.getNetProfit() == null ||  // 순이익
                indutyVariable.indutyDetail.getTax() == null ||  // 세금
                indutyVariable.indutyDetail.getInterestExpense() == null || // 이지비용
                indutyVariable.indutyDetail.getEquityCapital() == null ||  // 자기자본 시장가치
                indutyVariable.indutyDetail.getSales() == null // 매출액

        ) {isExistAll = false;}

        try {
            // 계산
            Double targetZScore =
                            0.012 * target.netWorkingCapital / target.corpDetail.getTotalAssets() + // (순운전자본 / 총자산)
                            0.014 * target.corpDetail.getRetainedEarnings() / target.corpDetail.getTotalAssets() + // (이익잉여금 / 총자산)
                            0.033 * (target.corpDetail.getNetProfit() + target.corpDetail.getTax() + target.corpDetail.getInterestExpense()) / target.corpDetail.getTotalAssets() +  //(EBIT / 총자산)
                            0.006 * target.corpDetail.getEquityCapital() / target.corpDetail.getTotalLiabilities()/ // (자기자본 시장가치 / 총부채)
                            0.0099 * target.corpDetail.getSales() / target.corpDetail.getTotalAssets(); // (매출액 / 총자산)
            Double indutyZScore =
                            0.012 * indutyVariable.netWorkingCapital / indutyVariable.indutyDetail.getTotalAssets() + // (순운전자본 / 총자산)
                            0.014 * indutyVariable.indutyDetail.getRetainedEarnings() / indutyVariable.indutyDetail.getTotalAssets() + // (이익잉여금 / 총자산)
                            0.033 * (indutyVariable.indutyDetail.getNetProfit() + indutyVariable.indutyDetail.getTax() + indutyVariable.indutyDetail.getInterestExpense()) / indutyVariable.indutyDetail.getTotalAssets() +  //(EBIT / 총자산)
                            0.006 * indutyVariable.indutyDetail.getEquityCapital() / indutyVariable.indutyDetail.getTotalLiabilities()/ // (자기자본 시장가치 / 총부채)
                            0.0099 * indutyVariable.indutyDetail.getSales() / indutyVariable.indutyDetail.getTotalAssets(); // (매출액 / 총자산)

            // Z-score
            HashMap data1 = new HashMap();
            data1.put(target.corp.getCorpName(), targetZScore);
            data1.put("name", "Z-score");
            data1.put("산업평균", indutyZScore);

            // 평가
            String rate;
            if (targetZScore <= 1.81) {rate = "부실";}
            else if (targetZScore <= 2.99 || targetZScore > 1.81) {rate = "보류";}
            else {rate = "정상";}

            data.add(data1);

            // 리턴값 정리
            HashMap result = new HashMap();
            result.put("is_exist_all", isExistAll);
            result.put("rate", rate);
            result.put("analysis_method", 405);
            result.put("corp_name", target.corp.getCorpName());
            result.put("corp_id", target.corp.getCorpId());
            result.put("analysis_name", "Z-Score 분석");
            result.put("result", data);
            log.info("Z-Score 분석(405) 완료");
            return result;
        } catch (Exception e) {
            // 리턴값 정리
            HashMap result = new HashMap();
            result.put("is_exist_all", isExistAll);
            result.put("analysis_method", 405);
            result.put("corp_name", target.corp.getCorpName());
            result.put("corp_id", target.corp.getCorpId());
            result.put("analysis_name", "Z-Score 분석");
            result.put("result","");
            log.info("Z-Score 분석(405) 완료");
            return result;
        }



    }

}
