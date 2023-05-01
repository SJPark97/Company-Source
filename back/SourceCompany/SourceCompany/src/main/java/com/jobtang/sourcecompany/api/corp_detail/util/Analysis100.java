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
public class Analysis100 {

    private final IndutyDetailRepository indutyDetailRepository;

    // 유동성분석 101
    public HashMap analysis101(CorpVariable target) {
        log.info("유동성분석(101) 진입");

//        1. 유동비율 = 유동자산/유동부채 * 100
//        2. 당좌비율 = 당좌자산/유동부채 * 100 = (유동자산-재고자산)/유동부채 * 100
//        3. 현금비율 = 현금및현금성자산/유동부채 * 100
//        4. 순운전자본비율 = 순운전자본/총자산 * 100 = (유동자산-유동부채)/총자산 * 100

        // 기본 변수 설정
        Boolean isExistAll = true;
        List data = new ArrayList();
        int goodCount = 0;

        // 산업 설정
        IndutyDetail indutyDetail = indutyDetailRepository.findByIndutyCode(target.corp.getIndutyCode());
        if (indutyDetail == null) {
            log.warn("해당 산업의 정보가 없습니다" + target.corp.getIndutyName());
            isExistAll = false;
        }

        // 타겟의 빈값이 있으면
        if (target.liquidityRatio == null || target.quickRatio == null || target.cashRatio == null || target.netWorkingCapital == null) {
            isExistAll = false;
        }
//         산업의 빈값이 있으면
        IndutyVariable indutyVariable = new IndutyVariable(indutyDetail);
        if (indutyVariable == null || indutyVariable.liquidityRatio == null || indutyVariable.quickRatio == null || indutyVariable.cashRatio == null || indutyVariable.netWorkingCapital == null) {
            isExistAll = false;
        }

        // 1) 유동비율
        HashMap data1 = new HashMap();
        data1.put(target.corp.getCorpName(), target.liquidityRatio);
        data1.put("name", "유동비율");
        data1.put("산업평균", indutyVariable.liquidityRatio);

        String rate1 = target.liquidityRatio >= 130 ? "양호" : "불량";
        if (rate1.equals("양호")) {goodCount += 1;}
        data1.put("평가", rate1);

        data.add(data1);

        // 2) 당좌비율
        HashMap data2 = new HashMap();
        data2.put(target.corp.getCorpName(), target.quickRatio);
        data2.put("name", "당좌비율");
        data2.put("산업평균", indutyVariable.quickRatio);

        String rate2 = target.quickRatio >= 80 ? "양호" : "불량";
        if (rate2.equals("양호")) {goodCount += 1;}
        data2.put("평가", rate2);

        data.add(data2);

        // 3) 현금비율
        HashMap data3 = new HashMap();
        data3.put(target.corp.getCorpName(), target.cashRatio);
        data3.put("name", "현금비율");
        data3.put("산업평균", indutyVariable.cashRatio);

        String rate3 = target.cashRatio >= 20 || target.cashRatio < 30 ? "양호" : "불량";
        if (rate3.equals("양호")) {goodCount += 1;}
        data3.put("평가", rate3);

        data.add(data3);

        // 4) 순운전자본비율
        HashMap data4 = new HashMap();
        data4.put(target.corp.getCorpName(), target.netWorkingCapital);
        data4.put("name", "순운전자본비율");
        data4.put("산업평균", indutyVariable.netWorkingCapital);

        String rate4 = target.netWorkingCapital >= indutyVariable.netWorkingCapital ? "양호" : "불량";
        if (rate4.equals("양호")) {goodCount += 1;}
        data4.put("평가", rate4);

        data.add(data4);

        // 리턴값 정리
        HashMap result = new HashMap();
        String totalRate = goodCount >= 2 ? "양호" : "불량";
        result.put("is_exist_all", isExistAll);
        result.put("rate", totalRate);
        result.put("analysis_method", 101);
        result.put("corp_name", target.corp.getCorpName());
        result.put("corp_id", target.corp.getCorpId());
        result.put("analysis_name", "유동성 분석");
        result.put("result", data);
        log.info("유동성분석(101) 완료");
        return result;
    }

    //자본배분의 안정성분석 103
    public HashMap analysis103(CorpVariable target) {
        log.info("자본배분의 안정성 분석(103) 진입");
//        1. 비유동비율 = 비유동자산/자기자본 * 100
//        2. 비유동장기적합률 = 비유동자산/(자기자본+비유동부채)*100

        // 기본 변수 설정
        Boolean isExistAll = true;
        List data = new ArrayList();
        int goodCount = 0;

        // 산업 설정
        IndutyDetail indutyDetail = indutyDetailRepository.findByIndutyCode(target.corp.getIndutyCode());
        if (indutyDetail == null) {
            log.warn("해당 산업의 정보가 없습니다" + target.corp.getIndutyName());
            isExistAll = false;
        }

        // 타겟의 빈값이 있으면
        if (target.nonCurrentRatio == null || target.nonCurrentAssetToStockholdersEquityAndNonCurrentLiability == null) {
            isExistAll = false;
        }
//         산업의 빈값이 있으면
        IndutyVariable indutyVariable = new IndutyVariable(indutyDetail);
        if (indutyVariable == null || indutyVariable.nonCurrentRatio == null || indutyVariable.nonCurrentAssetToStockholdersEquityAndNonCurrentLiability == null) {
            isExistAll = false;
        }

        // 1) 비유동비율
        HashMap data1 = new HashMap();
        data1.put(target.corp.getCorpName(), target.nonCurrentRatio);
        data1.put("name", "비유동비율");
        data1.put("산업평균", indutyVariable.nonCurrentRatio);
        System.out.println("값 : "+target.nonCurrentRatio.toString());

        String rate1 = target.nonCurrentRatio <= indutyVariable.nonCurrentRatio ? "양호" : "불량";
        if (rate1.equals("양호")) {goodCount += 1;}
        data1.put("평가", rate1);

        data.add(data1);

        // 2) 비유동장기적합률
        HashMap data2 = new HashMap();
        data2.put(target.corp.getCorpName(), target.nonCurrentAssetToStockholdersEquityAndNonCurrentLiability);
        data2.put("name", "비유동장기적합률");
        data2.put("산업평균", indutyVariable.nonCurrentAssetToStockholdersEquityAndNonCurrentLiability);

        String rate2 = target.nonCurrentAssetToStockholdersEquityAndNonCurrentLiability <= 100 ? "양호" : "불량";
        if (rate2.equals("양호")) {goodCount += 1;}
        data2.put("평가", rate2);

        data.add(data2);

        // 리턴값 정리
        HashMap result = new HashMap();
        String totalRate = goodCount >= 1 ? "양호" : "불량";
        result.put("is_exist_all", isExistAll);
        result.put("rate", totalRate);
        result.put("analysis_method", 103);
        result.put("corp_name", target.corp.getCorpName());
        result.put("corp_id", target.corp.getCorpId());
        result.put("analysis_name", "자본배분의 안정성 분석");
        result.put("result", data);
        log.info("자본배분의 안정성 분석(103) 진입");
        return result;
    }

}
// result : {"status101" : true,
//            "data101": [
//            {
//                "잡탕마을": 151.3,
//                    "name": "유동비율",
//                    "산업평균": 130.2,
//                    "평가": "양호"
//            },
//            {
//                "잡탕마을": 55.1,
//                    "name": "당좌비율",
//                    "산업평균": 80.4,
//                    "평가": "불량"
//            },
//            {
//                "잡탕마을": 9.6,
//                    "name": "현금비율",
//                    "산업평균": 20,
//                    "평가": "불량"
//            },
//            {
//                "잡탕마을": 19,
//                    "name": "순운전자본비율",
//                    "산업평균": 10,
//                    "평가": "양호"
//            }
//        ],
//            "rate": "보통",
//                    "analysis_name": "유동성 분석",
//                    "corp_id": "00425351",
//                    "analysis_method": 101
//        }
//        }
//    }
//  ]
//}