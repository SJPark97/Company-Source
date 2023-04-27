package com.jobtang.sourcecompany.api.corp_detail.util;

import com.jobtang.sourcecompany.api.corp.entity.Corp;
import com.jobtang.sourcecompany.api.corp_detail.entity.CorpDetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@RequiredArgsConstructor
public class Variable {

    @Autowired
    private Calculator calculator;

    public Corp corp;
    private CorpDetail c;

    // 주가정보
    private Integer marketCapitalization; // 시가총액
    private Double earningsPerShare; // 주당이익

    //  세부계산 항목
    private Integer netWorkingCapital; // 순운전자본 = 유동자산 - 유동부채
    private Double liquidityRatio; // 유동비율 = 유동자산/유동부채 * 100
    private Integer quickAsset;// 당좌자산 = 유동자산 - 재고자산
    private Double quickRatio; // 당좌비율 = 당좌자산/유동부채 * 100 = (유동자산-재고자산)/유동부채 * 100
    private Double cashRatio; // 현금비율 = 현금및현금성자산/유동부채 * 100
    private Double netWorkingCapitalToTotalAsset; // 순운전자본비율 = 순운전자본/총자산 * 100 = (유동자산-유동부채)/총자산 * 100
    private Double nonCurrentRatio; // 비유동비율(고정비율) = 비유동자산/자기자본 * 100
    private Double nonCurrentAssetToStockholdersEquityAndNonCurrentLiability; // 비유동장기적합률(고정장기적합률) = 비유동자산/(자기자본 + 비유동부채) * 100
    private Double currentAssetCompositionRatio; // 유동자산구성비율 = 유동자산/총자산
    private Double tangibleAssetCompositionRatio; // 유형자산구성비율 = 유형자산/총자산
    private Double salesGrowthRate; // 매출액증가율 = (당기 매출액 - 전기 매출액) / 전기 매출액 * 100
    private Double totalAssetGrowthRate; // 총자산증가율 = (당기말 총자산 - 전기말 총자산) / 전기말 총자산 * 100
    private Double netWorthGrowthRate; // 자기자본증가율 = (당기말 자기자본-전기말 자기자본) / 전기말 자기자본 * 100
    private Double netProfitGrowthRate; // 순이익증가율 = (당기 순이익 - 전기 순이익) / 전기 순이익
    private Double earningsPerShareGrowthRate; // 주당이익증가율 = (당기 주당이익 - 전기 주당이익) / 전기 주당이익 * 100
    private Double reserveRatio; // 유보율 = 세전순이익/납입자본금 * 100 = (당기순이익 - 전기순이익) / 전기순이익 * ROE
    private Double sustainableGrowthRate; // 지속가능성장률 = 유보율 * 자기자본순이익률 = b * ROE
    private Double priceEarningRatio; // PER = 시가총액/순이익
    private Double priceBookValueRatio; // PBR = 시가총액/자기자본
    private Double priceSalesRatio; // PSR = 시가총액/매출액
    private Double returnOnInvestment; // 자기자본순이익률(ROI) = 순이익/매출액 * 매출액/총자산
    private Double debtToEquityRatio; // 부채비율 = 총부채/자기자본
    private Double returnOnEquity; // ROE = ROI * (1 + 부채비율)
    private Double netProfitBeforeTax; // 세전순이익 = 순이익 + 세금
//    private Double turnoverRatioOfTotalOperatingCapital; // 경영자본 = 총자산 - 투자자산 - 건설중인자산 = 경영자본회전율

    // 생성자
    public Variable(Corp corp) {
        this.corp = corp;
        this.c = corp.getCorpDetail();
        calculatevVriables();
        log.info("기업정보 생성완료 : "+corp.getCorpName());
    }

    // 변수값 계산하기
    private void calculatevVriables() {
        this.netWorkingCapital = calculator.mySubtraction(c.getCurrentAsset(), c.getCurrentLiabilities()); // 순운전자본 = 유동자산 - 유동부채
        this.liquidityRatio = calculator.myRatio(c.getCurrentAsset(), c.getCurrentLiabilities()); // 유동비율 = 유동자산/유동부채 * 100
        this.quickAsset = calculator.mySubtraction(c.getCurrentAsset(), c.getInventories());// 당좌자산 = 유동자산 - 재고자산
        this.quickRatio = calculator.myRatio(quickAsset, c.getCurrentLiabilities()); // 당좌비율 = 당좌자산/유동부채 * 100 = (유동자산-재고자산)/유동부채 * 100
        this.cashRatio = calculator.myRatio(c.getCashAndCashEquivalents(), c.getCurrentLiabilities()); // 현금비율 = 현금및현금성자산/유동부채 * 100
        this.netWorkingCapitalToTotalAsset = calculator.myRatio(netWorkingCapital, c.getTotalAssets()); // 순운전자본비율 = 순운전자본/총자산 * 100 = (유동자산-유동부채)/총자산 * 100
        this.nonCurrentRatio = calculator.myRatio(c.getNonCurrentAssets(), c.getEquityCapital()); // 비유동비율(고정비율) = 비유동자산/자기자본 * 100
        this.nonCurrentAssetToStockholdersEquityAndNonCurrentLiability = calculator.myRatioWithSum(c.getNonCurrentAssets(), c.getEquityCapital(), c.getNonCurrentLiabilities()); // 비유동장기적합률(고정장기적합률) = 비유동자산/(자기자본 + 비유동부채) * 100
        this.currentAssetCompositionRatio = calculator.myDivision(c.getCurrentAsset(), c.getTotalAssets()); // 유동자산구성비율 = 유동자산/총자산
        this.tangibleAssetCompositionRatio = calculator.myDivision(c.getTangibleAssets(), c.getTotalAssets()); // 유형자산구성비율 = 유형자산/총자산
        this.salesGrowthRate = calculator.myRatioWithSubtraction(c.getSales(), c.getPreviousSales(), c.getPreviousSales()); // 매출액증가율 = (당기 매출액 - 전기 매출액) / 전기 매출액 * 100
        this.totalAssetGrowthRate = calculator.myRatioWithSubtraction(c.getTotalAssets(), c.getPreviousTotalAssets(), c.getPreviousTotalAssets()); // 총자산증가율 = (당기말 총자산 - 전기말 총자산) / 전기말 총자산 * 100
        this.netWorthGrowthRate = calculator.myRatioWithSubtraction(c.getEquityCapital(), c.getPreviousEquityCapital(), c.getPreviousEquityCapital()); // 자기자본증가율 = (당기말 자기자본-전기말 자기자본) / 전기말 자기자본 * 100
        this.netProfitGrowthRate = calculator.myRatioWithSubtraction(c.getNetProfit(), c.getPreviousNetProfit(), c.getPreviousNetProfit()); // 순이익증가율 = (당기 순이익 - 전기 순이익) / 전기 순이익 * 100
        this.earningsPerShareGrowthRate = calculator.myRatioWithSubtraction(c.getPreviousEarningPerShare(), c.getPreviousEarningPerShare(), c.getPreviousEarningPerShare()); // 주당이익증가율 = (당기 주당이익 - 전기 주당이익) / 전기 주당이익 * 100
        this.returnOnInvestment = calculator.myDivision(c.getNetProfit(), c.getEquityCapital()); // 자기자본순이익률(ROI) = 순이익/매출액 * 매출액/총자산 = 순이익/총자본
        this.debtToEquityRatio = calculator.myDivision(c.getTotalLiabilities(), c.getEquityCapital()); // 부채비율 = 총부채/자기자본
        this.returnOnEquity = calculator.myMultiplyWithSum(returnOnInvestment, 1, debtToEquityRatio); // ROE = ROI * (1 + 부채비율)
        this.netProfitBeforeTax = calculator.myPlus(c.getNetProfit(), c.getTax()); // 세전순이익 = 순이익 + 세금
        this.reserveRatio = calculator.myCalculate(c.getNetProfit(), c.getPreviousNetProfit(), c.getPreviousNetProfit(), returnOnEquity); // 유보율 = 세전순이익/납입자본금 * 100 = 유보율 = (당기순이익 - 전기순이익) / 전기순이익 * ROE
        this.sustainableGrowthRate = calculator.myMultiply(reserveRatio, returnOnEquity); // 지속가능성장률 = 유보율 * 자기자본순이익률 = b * ROE
        this.priceEarningRatio = calculator.myDivision(marketCapitalization, c.getNetProfit()); // PER = 시가총액/순이익
        this.priceBookValueRatio = calculator.myDivision(marketCapitalization, c.getEquityCapital()); // PBR = 시가총액/자기자본
        this.priceSalesRatio = calculator.myDivision(marketCapitalization, c.getSales()); // PSR = 시가총액/매출액
//        this.turnoverRatioOfTotalOperatingCapital = c.getTotalAssets(); // 경영자본 = 총자산 - 투자자산 - 건설중인자산 = 경영자본회전율
    }
}
