package com.jobtang.sourcecompany.api.corp_detail.service;

import com.jobtang.sourcecompany.api.corp_detail.entity.CorpDetail;
import com.jobtang.sourcecompany.api.corp_detail.repository.CorpDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CorpDetailServiceImpl implements CorpDetailService{

    private final CorpDetailRepository corpDetailRepository;

    //  주식 데이터
    private Integer marketCapitalization; // 시가총액
    private Float earningsPerShare; // 주당이익
    // 유보율

    //  세부계산 항목
    private Integer netWorkingCapital; // 순운전자본 = 유동자산 - 유동부채
    private Float liquidityRatio; // 유동비율 = 유동자산/유동부채 * 100
    private Integer quickAsset;// 당좌자산 = 유동자산 - 재고자산
    private Float quickRatio; // 당좌비율 = 당좌자산/유동부채 * 100 = (유동자산-재고자산)/유동부채 * 100
    private Float cashRatio; // 현금비율 = 현금및현금성자산/유동부채 * 100
    private Float netWorkingCapitalToTotalAsset; // 순운전자본비율 = 순운전자본/총자산 * 100 = (유동자산-유동부채)/총자산 * 100
    private Float nonCurrentRatio; // 비유동비율(고정비율) = 비유동자산/자기자본 * 100
    private Float nonCurrentAssetToStockholdersEquityAndNonCurrentLiability; // 비유동장기적합률(고정장기적합률) = 비유동자산/(자기자본 + 비유동부채) * 100
    private Float currentAssetCompositionRatio; // 유동자산구성비율 = 유동자산/총자산
    private Float tangibleAssetCompositionRatio; // 유형자산구성비율 = 유형자산/총자산
    private Float salesGrowthRate; // 매출액증가율 = (당기 매출액 - 전기 매출액) / 전기 매출액 * 100
    private Float totalAssetGrowthRate; // 총자산증가율 = (당기말 총자산 - 전기말 총자산) / 전기말 총자산 * 100
    private Float netWorthGrowthRate; // 자기자본증가율 = (당기말 자기자본-전기말 자기자본) / 전기말 자기자본 * 100
    private Float netProfitGrowthRate; // 순이익증가율 = (당기 순이익 - 전기 순이익) / 전기 순이익
    private Float earningsPerShareGrowthRate; // 주당이익증가율 = (당기 주당이익 - 전기 주당이익) / 전기 주당이익 * 100
    private Float reserveRatio; // 유보율 = 세전순이익/납입자본금 * 100 = (당기순이익 - 전기순이익) / 전기순이익 * ROE
    private Float sustainableGrowthRate; // 지속가능성장률 = 유보율 * 자기자본순이익률 = b * ROE
    private Float priceEarningRatio; // PER = 시가총액/순이익
    private Float priceBookValueRatio; // PBR = 시가총액/자기자본
    private Float priceSalesRatio; // PSR = 시가총액/매출액
    private Float returnOnInvestment; // 자기자본순이익률(ROI) = 순이익/매출액 * 매출액/총자산
    private Float debtToEquityRatio; // 부채비율 = 총부채/자기자본
    private Float returnOnEquity; // ROE = ROI * (1 + 부채비율)
    private Float netProfitBeforeTax; // 세전순이익 = 순이익 + 세금
    private Float turnoverRatioOfTotalOperatingCapital; // 경영자본 = 총자산 - 투자자산 - 건설중인자산 = 경영자본회전율

    @Override
    public void updateCorpDetails(String corpId) {
        // 사용할 기업의 정보 가져오기
        CorpDetail corpDetail = corpDetailRepository.findByCorpDetailId(corpId);
        if (corpDetail == null) {log.warn("기업의 재무재표가 존재하지 않아 패스합니다 "+corpId);}

//        calculatevVriables(corpDetail);

    }

//    private void calculatevVriables(CorpDetail c) {
//        this.netWorkingCapital = (c.getCurrentAsset() == null || c.getCurrentLiabilities() == null) ? null : c.getCurrentAsset() - c.getCurrentLiabilities(); // 순운전자본 = 유동자산 - 유동부채
//        this.liquidityRatio = (c.getCurrentAsset() == null || c.getCurrentLiabilities() == null) ? null : ((float) c.getCurrentAsset()/c.getCurrentLiabilities()) * 100; // 유동비율 = 유동자산/유동부채 * 100
//        this.quickAsset = (c.getCurrentAsset() == null ||c.getInventories() == null) ? null : c.getCurrentAsset() - c.getInventories();// 당좌자산 = 유동자산 - 재고자산
//        this.quickRatio = (quickAsset == null || c.getCurrentLiabilities() == null) ? null : (float) quickAsset/c.getCurrentLiabilities() * 100; // 당좌비율 = 당좌자산/유동부채 * 100 = (유동자산-재고자산)/유동부채 * 100
//        this.cashRatio = (c.getCashAndCashEquivalents() == null || c.getCurrentLiabilities() == null) ? null : (float) c.getCashAndCashEquivalents()/c.getCurrentLiabilities() * 100; // 현금비율 = 현금및현금성자산/유동부채 * 100
//        this.netWorkingCapitalToTotalAsset = (netWorkingCapital == null || c.getTotalAssets()  == null) ? null : (float) netWorkingCapital/c.getTotalAssets() * 100; // 순운전자본비율 = 순운전자본/총자산 * 100 = (유동자산-유동부채)/총자산 * 100
//        this.nonCurrentRatio = ( == null ||  == null) ? null : (float)  c.getNonCurrentAssets()/c.getEquityCapital() * 100; // 비유동비율(고정비율) = 비유동자산/자기자본 * 100
//        this.nonCurrentAssetToStockholdersEquityAndNonCurrentLiability = ( == null ||  == null) ? null : (float)  c.getNonCurrentAssets()/(c.getEquityCapital() + c.getNonCurrentLiabilities()) * 100; // 비유동장기적합률(고정장기적합률) = 비유동자산/(자기자본 + 비유동부채) * 100
//        this.currentAssetCompositionRatio = ( == null ||  == null) ? null : (float) c.getCurrentAsset()/c.getTotalAssets(); // 유동자산구성비율 = 유동자산/총자산
//        this.tangibleAssetCompositionRatio = ( == null ||  == null) ? null : (float)  c.getTangibleAssets()/c.getTotalAssets(); // 유형자산구성비율 = 유형자산/총자산
//        this.salesGrowthRate = ( == null ||  == null) ? null : (float)  (c.getSales()-c.getPreviousSales())/c.getPreviousSales() * 100; // 매출액증가율 = (당기 매출액 - 전기 매출액) / 전기 매출액 * 100
//        this.totalAssetGrowthRate = ( == null ||  == null) ? null : (float)  (c.getTotalAssets() - c.getPreviousTotalAssets())/c.getPreviousTotalAssets() * 100; // 총자산증가율 = (당기말 총자산 - 전기말 총자산) / 전기말 총자산 * 100
//        this.netWorthGrowthRate = ( == null ||  == null) ? null : (float)  (c.getEquityCapital() - c.getPreviousEquityCapital())/c.getPreviousEquityCapital() * 100; // 자기자본증가율 = (당기말 자기자본-전기말 자기자본) / 전기말 자기자본 * 100
//        this.netProfitGrowthRate = ( == null ||  == null) ? null : (float)  (c.getNetProfit() - c.getPreviousNetProfit())/c.getPreviousNetProfit(); // 순이익증가율 = (당기 순이익 - 전기 순이익) / 전기 순이익
//        this.earningsPerShareGrowthRate = ( == null ||  == null) ? null : (float) (c.getPreviousEarningPerShare() - c.getPreviousEarningPerShare())/c.getPreviousEarningPerShare() * 100; // 주당이익증가율 = (당기 주당이익 - 전기 주당이익) / 전기 주당이익 * 100
//        this.returnOnInvestment = ( == null ||  == null) ? null : (float)  c.getNetProfit()/c.getSales() * c.getSales()/c.getTotalAssets(); // 자기자본순이익률(ROI) = 순이익/매출액 * 매출액/총자산
//        this.debtToEquityRatio = ( == null ||  == null) ? null  (float)  c.getTotalLiabilities()/c.getEquityCapital(); // 부채비율 = 총부채/자기자본
//        this.returnOnEquity = ( == null ||  == null) ? null : (float) returnOnInvestment * (1 + debtToEquityRatio); // ROE = ROI * (1 + 부채비율)
//        this.netProfitBeforeTax = ( == null ||  == null) ? null : (float)  c.getNetProfit() + c.getTax(); // 세전순이익 = 순이익 + 세금
//        this.reserveRatio = ( == null ||  == null) ? null  (float) (c.getNetProfit() - c.getPreviousNetProfit())/c.getPreviousNetProfit() * returnOnEquity; // 유보율 = 세전순이익/납입자본금 * 100 = 유보율 = (당기순이익 - 전기순이익) / 전기순이익 * ROE
//        this.sustainableGrowthRate = ( == null ||  == null) ? null : (float) reserveRatio * returnOnEquity; // 지속가능성장률 = 유보율 * 자기자본순이익률 = b * ROE
//        this.priceEarningRatio = ( == null ||  == null) ? null : (float) marketCapitalization/c.getNetProfit(); // PER = 시가총액/순이익
//        this.priceBookValueRatio = ( == null ||  == null) ? null : (float) marketCapitalization/c.getEquityCapital(); // PBR = 시가총액/자기자본
//        this.priceSalesRatio = ( == null ||  == null) ? null : (float) marketCapitalization/c.getSales(); // PSR = 시가총액/매출액
////        this.turnoverRatioOfTotalOperatingCapital = c.getTotalAssets(); // 경영자본 = 총자산 - 투자자산 - 건설중인자산 = 경영자본회전율
//    }

//    private HashMap analysis101(CorpDetail c){
//        List result = new ArrayList();
//        String corpName = c.getCorp().getCorpName();
//
//        if (liquidityRatio == null || ) {
//            return null;
//        }
//
//
//        result.add();
//
//        return result;

//        {
//            "": 200,
//                "data": {
//            "result": [
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
//        },
//            "message": ""
//        }
//    }
}
