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
        try {
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
            data4.put(target.corp.getCorpName(), target.netWorkingCapitalToTotalAsset);
            data4.put("name", "순운전자본비율");
            data4.put("산업평균", indutyVariable.netWorkingCapitalToTotalAsset);

            String rate4 = target.netWorkingCapitalToTotalAsset >= indutyVariable.netWorkingCapitalToTotalAsset ? "양호" : "불량";

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
        } catch (Exception e) {
            // 리턴값 정리
            HashMap result = new HashMap();
            result.put("is_exist_all", isExistAll);
            result.put("analysis_method", 101);
            result.put("corp_name", target.corp.getCorpName());
            result.put("corp_id", target.corp.getCorpId());
            result.put("analysis_name", "유동성 분석");
            result.put("result","");
            log.info("유동성분석(101) 실패");
            return result;
        }


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
        try {
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
            log.info("자본배분의 안정성 분석(103) 성공");
            return result;
        } catch (Exception e) {
            // 리턴값 정리
            HashMap result = new HashMap();
            result.put("is_exist_all", isExistAll);
            result.put("analysis_method", 103);
            result.put("corp_name", target.corp.getCorpName());
            result.put("corp_id", target.corp.getCorpId());
            result.put("analysis_name", "자본배분의 안정성 분석");
            result.put("result", "");
            log.info("자본배분의 안정성 분석(103) 실패");
            return result;
        }

    }

    //자산구성 분석 104 + 수정예정
    public HashMap analysis104(CorpVariable target) {
        log.info("자산구성 분석(104) 진입");
//        1. 유동자산구성비율 = 유동자산/총자산 * 100
//        2. 유형자산구성비율 = 유형자산/총자산 * 100

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
        if (target.currentAssetCompositionRatio == null || target.tangibleAssetCompositionRatio == null) {
            isExistAll = false;
        }
//         산업의 빈값이 있으면
        IndutyVariable indutyVariable = new IndutyVariable(indutyDetail);
        if (indutyVariable == null || indutyVariable.currentAssetCompositionRatio == null || indutyVariable.tangibleAssetCompositionRatio == null) {
            isExistAll = false;
        }
        try {
            // 1) 유동자산구성비율
            HashMap data1 = new HashMap();
            data1.put(target.corp.getCorpName(), target.currentAssetCompositionRatio);
            data1.put("name", "유동자산구성비율");
            data1.put("산업평균", indutyVariable.currentAssetCompositionRatio);
            System.out.println("값 : "+target.currentAssetCompositionRatio.toString());

            String rate1 = target.currentAssetCompositionRatio >= indutyVariable.currentAssetCompositionRatio * 0.8 ||
                    target.currentAssetCompositionRatio <= indutyVariable.currentAssetCompositionRatio * 1.2 ? "양호" : "보통";
            if (rate1.equals("양호")) {goodCount += 1;}
            data1.put("평가", rate1);

            data.add(data1);

            // 2) 유형자산구성비율
            HashMap data2 = new HashMap();
            data2.put(target.corp.getCorpName(), target.tangibleAssetCompositionRatio);
            data2.put("name", "유형자산구성비율");
            data2.put("산업평균", indutyVariable.tangibleAssetCompositionRatio);

            String rate2 = target.tangibleAssetCompositionRatio <= 100 ? "양호" : "보통";
            if (rate2.equals("양호")) {goodCount += 1;}
            data2.put("평가", rate2);

            data.add(data2);

            // 리턴값 정리
            HashMap result = new HashMap();
            String totalRate = goodCount >= 2 ? "양호" : "보통";
            result.put("is_exist_all", isExistAll);
            result.put("rate", totalRate);
            result.put("analysis_method", 104);
            result.put("corp_name", target.corp.getCorpName());
            result.put("corp_id", target.corp.getCorpId());
            result.put("analysis_name", "자산구성 분석");
            result.put("result", data);
            log.info("자산구성 분석(104) 성공");
            return result;
        } catch (Exception e) {
            // 리턴값 정리
            HashMap result = new HashMap();
            result.put("is_exist_all", isExistAll);
            result.put("analysis_method", 104);
            result.put("corp_name", target.corp.getCorpName());
            result.put("corp_id", target.corp.getCorpId());
            result.put("analysis_name", "자산구성 분석");
            result.put("result", "");
            log.info("자산구성 분석(104) 실패");
            return result;
        }

    }

    //활동성 분석 106
//    public HashMap analysis106(CorpVariable target) {
////        1. 유동자산구성비율 = 유동자산/총자산 * 100
////        2. 유형자산구성비율 = 유형자산/총자산 * 100
//
//        // 기본 변수 설정
//        String analysisName = "활동성 분석";
//        int analysisNum = 106;
//        Boolean isExistAll = true;
//        List data = new ArrayList();
//        int goodCount = 0;
//
//        log.info(analysisName+"("+String.valueOf(analysisNum)+") 진입");
//
//        // 산업 설정
//        IndutyDetail indutyDetail = indutyDetailRepository.findByIndutyCode(target.corp.getIndutyCode());
//        if (indutyDetail == null) {
//            log.warn("해당 산업의 정보가 없습니다" + target.corp.getIndutyName());
//            isExistAll = false;

//        }
//
//        // 타겟의 빈값이 있으면
//        if (target.currentAssetCompositionRatio == null || target.tangibleAssetCompositionRatio == null) {
//            isExistAll = false;
//        }
////         산업의 빈값이 있으면
//        IndutyVariable indutyVariable = new IndutyVariable(indutyDetail);
//        if (indutyVariable == null || indutyVariable.currentAssetCompositionRatio == null || indutyVariable.tangibleAssetCompositionRatio == null) {
//            isExistAll = false;
//        }
//        try {
//            // 1) 유동자산구성비율
//            HashMap data1 = new HashMap();
//            data1.put(target.corp.getCorpName(), target.currentAssetCompositionRatio);
//            data1.put("name", "유동자산구성비율");
//            data1.put("산업평균", indutyVariable.currentAssetCompositionRatio);
//            System.out.println("값 : "+target.currentAssetCompositionRatio.toString());
//
//            String rate1 = target.currentAssetCompositionRatio >= indutyVariable.currentAssetCompositionRatio * 0.8 ||
//                    target.currentAssetCompositionRatio <= indutyVariable.currentAssetCompositionRatio * 1.2 ? "양호" : "보통";
//            if (rate1.equals("양호")) {goodCount += 1;}
//            data1.put("평가", rate1);
//
//            data.add(data1);
//
//
//            // 리턴값 정리
//            HashMap result = new HashMap();
//            String totalRate = goodCount >= 3 ? "양호" : "불량";
//            result.put("is_exist_all", isExistAll);
//            result.put("rate", totalRate);
//            result.put("analysis_method", analysisNum);
//            result.put("corp_name", target.corp.getCorpName());
//            result.put("corp_id", target.corp.getCorpId());
//            result.put("analysis_name", analysisName);
//            result.put("result", data);
//            log.info(analysisName+"("+String.valueOf(analysisNum)+") 성공");
//            return result;
//        } catch (Exception e) {
//            // 리턴값 정리
//            HashMap result = new HashMap();
//            result.put("is_exist_all", isExistAll);
//            result.put("analysis_method", analysisNum);
//            result.put("corp_name", target.corp.getCorpName());
//            result.put("corp_id", target.corp.getCorpId());
//            result.put("analysis_name", analysisName);
//            result.put("result", "");
//            log.info(analysisName+"("+String.valueOf(analysisNum)+") 실패");
//            return result;
//        }
//
//    }

//    주가이익비율(PER) 109
    public HashMap analysis109(CorpVariable target) {
//

        // 기본 변수 설정
        String analysisName = "주가이익비율";
        int analysisNum = 109;
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
        if (target.priceEarningRatio == null) {
            isExistAll = false;
        }
//         산업의 빈값이 있으면
        IndutyVariable indutyVariable = new IndutyVariable(indutyDetail);
        if (indutyVariable == null || indutyVariable.priceEarningRatio == null) {
            isExistAll = false;
        }
        try {
            // 1) 주가이익비율
            HashMap data1 = new HashMap();
            data1.put(target.corp.getCorpName(), target.priceEarningRatio);
            data1.put("name", "주가이익비율(PER)");
            data1.put("동업종", indutyVariable.priceEarningRatio);
            System.out.println("값 : "+target.priceEarningRatio.toString());
            String rate1 = target.priceEarningRatio >= indutyVariable.priceEarningRatio ? "양호" : "보통";

            if (rate1.equals("양호")) {goodCount += 1;}
            data1.put("평가", rate1);

            data.add(data1);


            // 리턴값 정리
            HashMap result = new HashMap();
            String totalRate = goodCount >= 1 ? "양호" : "보통";
            result.put("is_exist_all", isExistAll);
            result.put("rate", totalRate);
            result.put("analysis_method", analysisNum);
            result.put("corp_name", target.corp.getCorpName());
            result.put("corp_id", target.corp.getCorpId());
            result.put("analysis_name", analysisName);
            result.put("result", data);
            log.info(analysisName+"("+String.valueOf(analysisNum)+") 성공");
            return result;
        } catch (Exception e) {
            // 리턴값 정리
            HashMap result = new HashMap();
            result.put("is_exist_all", isExistAll);
            result.put("analysis_method", analysisNum);
            result.put("corp_name", target.corp.getCorpName());
            result.put("corp_id", target.corp.getCorpId());
            result.put("analysis_name", analysisName);
            result.put("result", "");
            log.info(analysisName+"("+String.valueOf(analysisNum)+") 실패");
            return result;
        }

    }

    //    주가순자산비율(PBR) 110
    public HashMap analysis110(CorpVariable target) {
        // 기본 변수 설정
        String analysisName = "주가순자산비율";
        int analysisNum = 110;
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
        if (target.priceBookValueRatio == null) {
            isExistAll = false;
        }
//         산업의 빈값이 있으면
        IndutyVariable indutyVariable = new IndutyVariable(indutyDetail);
        if (indutyVariable == null || indutyVariable.priceBookValueRatio == null) {
            isExistAll = false;
        }
        try {
            // 1) 주가순자산비율
            HashMap data1 = new HashMap();
            data1.put(target.corp.getCorpName(), target.priceBookValueRatio);
            data1.put("name", "주가순자산비율(PBR)");
            data1.put("동업종", indutyVariable.priceBookValueRatio);
            System.out.println("값 : "+target.priceBookValueRatio.toString());
            String rate1 = target.priceBookValueRatio >= indutyVariable.priceBookValueRatio ? "고평가" : "저평가";

            if (rate1.equals("고평가")) {goodCount += 1;}
            data1.put("평가", rate1);

            data.add(data1);


            // 리턴값 정리
            HashMap result = new HashMap();
            String totalRate = goodCount >= 1 ? "고평가" : "저평가";
            result.put("is_exist_all", isExistAll);
            result.put("rate", totalRate);
            result.put("analysis_method", analysisNum);
            result.put("corp_name", target.corp.getCorpName());
            result.put("corp_id", target.corp.getCorpId());
            result.put("analysis_name", analysisName);
            result.put("result", data);
            log.info(analysisName+"("+String.valueOf(analysisNum)+") 성공");
            return result;
        } catch (Exception e) {
            // 리턴값 정리
            HashMap result = new HashMap();
            result.put("is_exist_all", isExistAll);
            result.put("analysis_method", analysisNum);
            result.put("corp_name", target.corp.getCorpName());
            result.put("corp_id", target.corp.getCorpId());
            result.put("analysis_name", analysisName);
            result.put("result", "");
            log.info(analysisName+"("+String.valueOf(analysisNum)+") 실패");
            return result;
        }

    }

    //    주가매출액비율(PSR) 111
    public HashMap analysis111(CorpVariable target) {
        // 기본 변수 설정
        String analysisName = "주가매출액비율";
        int analysisNum = 111;
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
        if (target.priceSalesRatio == null) {
            isExistAll = false;
        }
//         산업의 빈값이 있으면
        IndutyVariable indutyVariable = new IndutyVariable(indutyDetail);
        if (indutyVariable == null || indutyVariable.priceSalesRatio == null) {
            isExistAll = false;
        }
        try {
            // 1) 주가순자산비율
            HashMap data1 = new HashMap();
            data1.put(target.corp.getCorpName(), target.priceSalesRatio);
            data1.put("name", "주가매출액비율(PSR)");
            data1.put("동업종", indutyVariable.priceSalesRatio);
            System.out.println("값 : "+target.priceSalesRatio.toString());
            String rate1 = target.priceSalesRatio >= indutyVariable.priceSalesRatio ? "고평가" : "저평가";

            if (rate1.equals("고평가")) {goodCount += 1;}
            data1.put("평가", rate1);

            data.add(data1);


            // 리턴값 정리
            HashMap result = new HashMap();
            String totalRate = goodCount >= 1 ? "고평가" : "저평가";
            result.put("is_exist_all", isExistAll);
            result.put("rate", totalRate);
            result.put("analysis_method", analysisNum);
            result.put("corp_name", target.corp.getCorpName());
            result.put("corp_id", target.corp.getCorpId());
            result.put("analysis_name", analysisName);
            result.put("result", data);
            log.info(analysisName+"("+String.valueOf(analysisNum)+") 성공");
            return result;
        } catch (Exception e) {
            // 리턴값 정리
            HashMap result = new HashMap();
            result.put("is_exist_all", isExistAll);
            result.put("analysis_method", analysisNum);
            result.put("corp_name", target.corp.getCorpName());
            result.put("corp_id", target.corp.getCorpId());
            result.put("analysis_name", analysisName);
            result.put("result", "");
            log.info(analysisName+"("+String.valueOf(analysisNum)+") 실패");
            return result;
        }

    }

    //    ROI 분석 113
    public HashMap analysis113(CorpVariable target) {
        // 기본 변수 설정
        String analysisName = "ROI분석";
        int analysisNum = 113;
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
        if (target.returnOnInvestment == null || target.returnOnEquity == null) {
            isExistAll = false;
        }
        //   산업의 빈값이 있으면
        IndutyVariable indutyVariable = new IndutyVariable(indutyDetail);
        if (indutyVariable == null || indutyVariable.returnOnInvestment == null || indutyVariable.returnOnEquity == null) {
            isExistAll = false;
        }
        try {
            // 1) ROI
            HashMap data1 = new HashMap();
            data1.put(target.corp.getCorpName(), target.returnOnInvestment);
            data1.put("name", "ROI");
            data1.put("동업종", indutyVariable.returnOnInvestment);
            System.out.println("값 : "+target.returnOnInvestment.toString());
            String rate1 = target.returnOnInvestment >= indutyVariable.returnOnInvestment ? "양호" : "불량";

            if (rate1.equals("양호")) {goodCount += 1;}
            data1.put("평가", rate1);

            data.add(data1);

            // 2) ROE
            HashMap data2 = new HashMap();
            data2.put(target.corp.getCorpName(), target.returnOnInvestment);
            data2.put("name", "ROE");
            data2.put("동업종", indutyVariable.returnOnInvestment);
            System.out.println("값 : "+target.returnOnInvestment.toString());
            String rate2 = target.returnOnInvestment >= indutyVariable.returnOnInvestment ? "양호" : "불량";

            if (rate2.equals("양호")) {goodCount += 2;}
            data2.put("평가", rate2);

            data.add(data2);


            // 리턴값 정리
            HashMap result = new HashMap();
            String totalRate = goodCount >= 1 ? "양호" : "불량";
            result.put("is_exist_all", isExistAll);
            result.put("rate", totalRate);
            result.put("analysis_method", analysisNum);
            result.put("corp_name", target.corp.getCorpName());
            result.put("corp_id", target.corp.getCorpId());
            result.put("analysis_name", analysisName);
            result.put("result", data);
            log.info(analysisName+"("+String.valueOf(analysisNum)+") 성공");
            return result;
        } catch (Exception e) {
            // 리턴값 정리
            HashMap result = new HashMap();
            result.put("is_exist_all", isExistAll);
            result.put("analysis_method", analysisNum);
            result.put("corp_name", target.corp.getCorpName());
            result.put("corp_id", target.corp.getCorpId());
            result.put("analysis_name", analysisName);
            result.put("result", "");
            log.info(analysisName+"("+String.valueOf(analysisNum)+") 실패");
            return result;
        }



    }
}
