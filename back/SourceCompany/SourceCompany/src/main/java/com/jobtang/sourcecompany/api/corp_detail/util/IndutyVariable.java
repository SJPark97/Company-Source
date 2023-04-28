package com.jobtang.sourcecompany.api.corp_detail.util;

import com.jobtang.sourcecompany.api.induty_detail.entity.IndutyDetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class IndutyVariable {

    public IndutyDetail indutyDetail;

    // 주가정보
    public Long marketCapitalization; // 시가총액
    public Double earningsPerShare; // 주당이익

    //  세부계산 항목
    public Long netWorkingCapital; // 순운전자본 = 유동자산 - 유동부채
    public Double liquidityRatio; // 유동비율 = 유동자산/유동부채 * 100
    public Long quickAsset;// 당좌자산 = 유동자산 - 재고자산
    public Double quickRatio; // 당좌비율 = 당좌자산/유동부채 * 100 = (유동자산-재고자산)/유동부채 * 100
    public Double cashRatio; // 현금비율 = 현금및현금성자산/유동부채 * 100
    public Double netWorkingCapitalToTotalAsset; // 순운전자본비율 = 순운전자본/총자산 * 100 = (유동자산-유동부채)/총자산 * 100
    public Double nonCurrentRatio; // 비유동비율(고정비율) = 비유동자산/자기자본 * 100
    public Double nonCurrentAssetToStockholdersEquityAndNonCurrentLiability; // 비유동장기적합률(고정장기적합률) = 비유동자산/(자기자본 + 비유동부채) * 100
    public Double currentAssetCompositionRatio; // 유동자산구성비율 = 유동자산/총자산
    public Double tangibleAssetCompositionRatio; // 유형자산구성비율 = 유형자산/총자산
    public Double salesGrowthRate; // 매출액증가율 = (당기 매출액 - 전기 매출액) / 전기 매출액 * 100
    public Double totalAssetGrowthRate; // 총자산증가율 = (당기말 총자산 - 전기말 총자산) / 전기말 총자산 * 100
    public Double netWorthGrowthRate; // 자기자본증가율 = (당기말 자기자본-전기말 자기자본) / 전기말 자기자본 * 100
    public Double netProfitGrowthRate; // 순이익증가율 = (당기 순이익 - 전기 순이익) / 전기 순이익
    public Double earningsPerShareGrowthRate; // 주당이익증가율 = (당기 주당이익 - 전기 주당이익) / 전기 주당이익 * 100
    public Double reserveRatio; // 유보율 = 세전순이익/납입자본금 * 100 = (당기순이익 - 전기순이익) / 전기순이익 * ROE
    public Double sustainableGrowthRate; // 지속가능성장률 = 유보율 * 자기자본순이익률 = b * ROE
    public Double priceEarningRatio; // PER = 시가총액/순이익
    public Double priceBookValueRatio; // PBR = 시가총액/자기자본
    public Double priceSalesRatio; // PSR = 시가총액/매출액
    public Double returnOnInvestment; // 자기자본순이익률(ROI) = 순이익/매출액 * 매출액/총자산
    public Double debtToEquityRatio; // 부채비율 = 총부채/자기자본
    public Double returnOnEquity; // ROE = ROI * (1 + 부채비율)
    public Double netProfitBeforeTax; // 세전순이익 = 순이익 + 세금
//    public Double turnoverRatioOfTotalOperatingCapital; // 경영자본 = 총자산 - 투자자산 - 건설중인자산 = 경영자본회전율

    // 생성자
    public IndutyVariable(IndutyDetail indutyDetail) {
        this.indutyDetail = indutyDetail;
        calculatevVriables();
        log.info("산업변수 생성완료 : "+indutyDetail.getIndutyCode());
    }

    // 변수값 계산하기
    private void calculatevVriables() {
        Calculator calculator = new Calculator();

        log.info("산업번수 계산");
        this.netWorkingCapital = calculator.mySubtraction(indutyDetail.getCurrentAsset(), indutyDetail.getCurrentLiabilities()); // 순운전자본 = 유동자산 - 유동부채
        this.liquidityRatio = calculator.myRatio(indutyDetail.getCurrentAsset(), indutyDetail.getCurrentLiabilities()); // 유동비율 = 유동자산/유동부채 * 100
        this.quickAsset = calculator.mySubtraction(indutyDetail.getCurrentAsset(), indutyDetail.getInventories());// 당좌자산 = 유동자산 - 재고자산
        this.quickRatio = calculator.myRatio(quickAsset, indutyDetail.getCurrentLiabilities()); // 당좌비율 = 당좌자산/유동부채 * 100 = (유동자산-재고자산)/유동부채 * 100
        this.cashRatio = calculator.myRatio(indutyDetail.getCashAndCashEquivalents(), indutyDetail.getCurrentLiabilities()); // 현금비율 = 현금및현금성자산/유동부채 * 100
        this.netWorkingCapitalToTotalAsset = calculator.myRatio(netWorkingCapital, indutyDetail.getTotalAssets()); // 순운전자본비율 = 순운전자본/총자산 * 100 = (유동자산-유동부채)/총자산 * 100
        this.nonCurrentRatio = calculator.myRatio(indutyDetail.getNonCurrentAssets(), indutyDetail.getEquityCapital()); // 비유동비율(고정비율) = 비유동자산/자기자본 * 100
        this.nonCurrentAssetToStockholdersEquityAndNonCurrentLiability = calculator.myRatioWithSum(indutyDetail.getNonCurrentAssets(), indutyDetail.getEquityCapital(), indutyDetail.getNonCurrentLiabilities()); // 비유동장기적합률(고정장기적합률) = 비유동자산/(자기자본 + 비유동부채) * 100
        this.currentAssetCompositionRatio = calculator.myDivision(indutyDetail.getCurrentAsset(), indutyDetail.getTotalAssets()); // 유동자산구성비율 = 유동자산/총자산
        this.tangibleAssetCompositionRatio = calculator.myDivision(indutyDetail.getTangibleAssets(), indutyDetail.getTotalAssets()); // 유형자산구성비율 = 유형자산/총자산
        this.salesGrowthRate = calculator.myRatioWithSubtraction(indutyDetail.getSales(), indutyDetail.getPreviousSales(), indutyDetail.getPreviousSales()); // 매출액증가율 = (당기 매출액 - 전기 매출액) / 전기 매출액 * 100
        this.totalAssetGrowthRate = calculator.myRatioWithSubtraction(indutyDetail.getTotalAssets(), indutyDetail.getPreviousTotalAssets(), indutyDetail.getPreviousTotalAssets()); // 총자산증가율 = (당기말 총자산 - 전기말 총자산) / 전기말 총자산 * 100
        this.netWorthGrowthRate = calculator.myRatioWithSubtraction(indutyDetail.getEquityCapital(), indutyDetail.getPreviousEquityCapital(), indutyDetail.getPreviousEquityCapital()); // 자기자본증가율 = (당기말 자기자본-전기말 자기자본) / 전기말 자기자본 * 100
        this.netProfitGrowthRate = calculator.myRatioWithSubtraction(indutyDetail.getNetProfit(), indutyDetail.getPreviousNetProfit(), indutyDetail.getPreviousNetProfit()); // 순이익증가율 = (당기 순이익 - 전기 순이익) / 전기 순이익 * 100
        this.earningsPerShareGrowthRate = calculator.myRatioWithSubtraction(indutyDetail.getPreviousEarningPerShare(), indutyDetail.getPreviousEarningPerShare(), indutyDetail.getPreviousEarningPerShare()); // 주당이익증가율 = (당기 주당이익 - 전기 주당이익) / 전기 주당이익 * 100
        this.returnOnInvestment = calculator.myDivision(indutyDetail.getNetProfit(), indutyDetail.getEquityCapital()); // 자기자본순이익률(ROI) = 순이익/매출액 * 매출액/총자산 = 순이익/총자본
        this.debtToEquityRatio = calculator.myDivision(indutyDetail.getTotalLiabilities(), indutyDetail.getEquityCapital()); // 부채비율 = 총부채/자기자본
        this.returnOnEquity = calculator.myMultiplyWithSum(returnOnInvestment, 1, debtToEquityRatio); // ROE = ROI * (1 + 부채비율)
        this.netProfitBeforeTax = calculator.myPlus(indutyDetail.getNetProfit(), indutyDetail.getTax()); // 세전순이익 = 순이익 + 세금
        this.reserveRatio = calculator.myCalculate(indutyDetail.getNetProfit(), indutyDetail.getPreviousNetProfit(), indutyDetail.getPreviousNetProfit(), returnOnEquity); // 유보율 = 세전순이익/납입자본금 * 100 = 유보율 = (당기순이익 - 전기순이익) / 전기순이익 * ROE
        this.sustainableGrowthRate = calculator.myMultiply(reserveRatio, returnOnEquity); // 지속가능성장률 = 유보율 * 자기자본순이익률 = b * ROE
        this.priceEarningRatio = calculator.myDivision(marketCapitalization, indutyDetail.getNetProfit()); // PER = 시가총액/순이익
        this.priceBookValueRatio = calculator.myDivision(marketCapitalization, indutyDetail.getEquityCapital()); // PBR = 시가총액/자기자본
        this.priceSalesRatio = calculator.myDivision(marketCapitalization, indutyDetail.getSales()); // PSR = 시가총액/매출액
//        this.turnoverRatioOfTotalOperatingCapital = c.getTotalAssets(); // 경영자본 = 총자산 - 투자자산 - 건설중인자산 = 경영자본회전율
    }
}
