package com.jobtang.sourcecompany.api.corp_detail.util.variable;

import com.jobtang.sourcecompany.api.corp_detail.util.Calculator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AnalysisVariable {

    public String variableId;
    public String variableName;
    public EntityVariable variable;

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
    public Double previousEarningPerShare; // 전기 주당순이익
    public Double earningPerShare; // 주당순이익


    // 버전2
    public Double netWorthToTotalAsset;  // 자기자본비율 = 자기자본/총자본 * 100
    public Double dependenceOnDebt;  // 차입금의존도 = (장단기 차입금 + 사채)/총자본 * 100
    public Double averageInterestRateOnBorrowings;  // 차입금평균이자율 = 이자비용/(장단기차입금+사채)의 평잔 * 100
    public Double timesInterestEarned; // 이자보상비율 = 영업이익/이자비용 > (영업이익/총자본)/(이자비용/차입금) = 총자본영업이익률/차입금평균이자율
    public Double ebitaInterestExpense; // EBITDA/이자비용비율 = EBITDA/이자비용 = (세전순이익 + 이자비용 + 감가상각비 및 무형자산상각비) / 이자비용
    public Double inventoryTurnover; // 재고자산회전율 = 매출액/재고자산(평잔)
    public Double totalAssetTurnover; // 총자산회전율 = 매출액/총자산(평잔)
    public Double operatingProfitToSalesRatio; // 매출액영업이익률 = 영업이익/매출액 * 100
    public Double totalAssetsOperatwingProfiRate; // 총자산영업이익률(ROA) = 총자산/영업이익
    public Double equityTurnover; // 자기자본회전율 = 매출액/자기자본(평잔)
    public Double nonCurrentAssetTurnover; // 비유동자산회전율 = 매출액/비유동자산(평잔)
    public Double receivablesTurnover; // 매출채권회전율 = 매출액/매출채권(평잔)
    public Double payablesTurnover; // 매입채무회전율 = 매출액/매입채무(평잔)
    public Double earningsAfterTaxToSalesRatio; // 매출액순이익율 = 당기순이익/매출액 * 100
    public Double returnOnAssets; // 총자산순이익율 = 순이익/총자산 * 100
    public Double cashFlowPerShare; // 주당현금흐름 = 주주현금흐름 / 발행주식수 = (당기순이익 + 현금의 유출이 없는 비용 - 현금의 유입이 없는 수익)
    public Double priceCashFlowRatio;// 주가현금흐름비율 = 주가/주당현금흐름
    public Double dividendSolvencyMultiple;// 배당지급능력배수 = 영업활동으로 인한 현금흐름 / 배당금
    public Double cashFlowToDebtRatio;// 현금흐름 대 차입금 비율 = 영업활동으로 인한 현금흐름 / 차입금(평잔) * 100
    public Double cashFlowToTotalDebtRatio;// 현금흐름 대 총부채 비율 = 영업활동으로 인한 현금흐름 / 총부채(평잔) * 100
    public Double cashFlowInterestCoverageRatio;// 현금흐름이자보상비율 = (영업활동으로 인한 현금흐름 + 이자비용) / 이자비용 * 100
    public Double cashFlow; // 현금흐름 = 영업활동으로 인한 현금흐름 + 재무활동으로 인한 현금흐름 + 투자활동으로 인한 현금흐름
    public Double turnoverRatioOfTotalOperatingCapital; // 경영자본 = 총자산 - 투자자산 - 건설중인자산 = 경영자본회전율

    // 생성자
    public AnalysisVariable(EntityVariable entityVariable) {
        // 기본 정보 등록
        this.variableId = entityVariable.getVariableId();
        this.variableName = entityVariable.getVariableName();
        this.variable = entityVariable;

        // 세부 정보 계산
        calculatevVriables();
        log.info("변수 생성완료 : "+variableName);
    }

    // 변수값 계산하기
    private void calculatevVriables() {
        Calculator calculator = new Calculator();
        this.netWorkingCapital = calculator.mySubtraction(variable.getCurrentAsset(), variable.getCurrentLiabilities()); // 순운전자본 = 유동자산 - 유동부채
        this.liquidityRatio = calculator.myRatio(variable.getCurrentAsset(), variable.getCurrentLiabilities()); // 유동비율 = 유동자산/유동부채 * 100
        this.quickAsset = calculator.mySubtraction(variable.getCurrentAsset(), variable.getInventories());// 당좌자산 = 유동자산 - 재고자산
        this.quickRatio = calculator.myRatio(quickAsset, variable.getCurrentLiabilities()); // 당좌비율 = 당좌자산/유동부채 * 100 = (유동자산-재고자산)/유동부채 * 100
        this.cashRatio = calculator.myRatio(variable.getCashAndCashEquivalents(), variable.getCurrentLiabilities()); // 현금비율 = 현금및현금성자산/유동부채 * 100
        this.netWorkingCapitalToTotalAsset = calculator.myRatio(netWorkingCapital, variable.getTotalAssets()); // 순운전자본비율 = 순운전자본/총자산 * 100 = (유동자산-유동부채)/총자산 * 100
        this.nonCurrentRatio = calculator.myRatio(variable.getNonCurrentAssets(), variable.getEquityCapital()); // 비유동비율(고정비율) = 비유동자산/자기자본 * 100
        this.nonCurrentAssetToStockholdersEquityAndNonCurrentLiability = calculator.myRatioWithSum(variable.getNonCurrentAssets(), variable.getEquityCapital(), variable.getNonCurrentLiabilities()); // 비유동장기적합률(고정장기적합률) = 비유동자산/(자기자본 + 비유동부채) * 100
        this.currentAssetCompositionRatio = calculator.myDivision(variable.getCurrentAsset(), variable.getTotalAssets()); // 유동자산구성비율 = 유동자산/총자산
        this.tangibleAssetCompositionRatio = calculator.myDivision(variable.getTangibleAssets(), variable.getTotalAssets()); // 유형자산구성비율 = 유형자산/총자산
        this.salesGrowthRate = calculator.myRatioWithSubtraction(variable.getSales(), variable.getPreviousSales(), variable.getPreviousSales()); // 매출액증가율 = (당기 매출액 - 전기 매출액) / 전기 매출액 * 100
        this.totalAssetGrowthRate = calculator.myRatioWithSubtraction(variable.getTotalAssets(), variable.getPreviousTotalAssets(), variable.getPreviousTotalAssets()); // 총자산증가율 = (당기말 총자산 - 전기말 총자산) / 전기말 총자산 * 100
        this.netWorthGrowthRate = calculator.myRatioWithSubtraction(variable.getEquityCapital(), variable.getPreviousEquityCapital(), variable.getPreviousEquityCapital()); // 자기자본증가율 = (당기말 자기자본-전기말 자기자본) / 전기말 자기자본 * 100
        this.netProfitGrowthRate = calculator.myRatioWithSubtraction(variable.getNetProfit(), variable.getPreviousNetProfit(), variable.getPreviousNetProfit()); // 순이익증가율 = (당기 순이익 - 전기 순이익) / 전기 순이익 * 100
        this.earningPerShare = calculator.myDivision(variable.getNetProfit(), variable.getNumberOfListedShares());
        this.previousEarningPerShare = calculator.myDivision(variable.getPreviousNetProfit(), variable.getPreviousNumberOfListedShares());
        this.earningsPerShareGrowthRate = calculator.myRatioWithSubtraction(earningPerShare, previousEarningPerShare, previousEarningPerShare); // 주당이익증가율 = (당기 주당이익 - 전기 주당이익) / 전기 주당이익 * 100
        this.returnOnInvestment = calculator.myDivision(variable.getNetProfit(), variable.getEquityCapital()); // 자기자본순이익률(ROI) = 순이익/매출액 * 매출액/총자산 = 순이익/총자본
        this.debtToEquityRatio = calculator.myDivision(variable.getTotalLiabilities(), variable.getEquityCapital()); // 부채비율 = 총부채/자기자본
        this.returnOnEquity = calculator.myMultiplyWithSum(returnOnInvestment, 1, debtToEquityRatio); // ROE = ROI * (1 + 부채비율)
        this.netProfitBeforeTax = calculator.myPlus(variable.getNetProfit(), variable.getTax()); // 세전순이익 = 순이익 + 세금
        this.reserveRatio = calculator.myCalculate(variable.getNetProfit(), variable.getPreviousNetProfit(), variable.getPreviousNetProfit(), returnOnEquity); // 유보율 = 세전순이익/납입자본금 * 100 = 유보율 = (당기순이익 - 전기순이익) / 전기순이익 * ROE
        this.sustainableGrowthRate = calculator.myMultiply(reserveRatio, returnOnEquity); // 지속가능성장률 = 유보율 * 자기자본순이익률 = b * ROE
        this.priceEarningRatio = calculator.myDivision(variable.getMarketCapitalization(), variable.getNetProfit()); // PER = 시가총액/순이익
        this.priceBookValueRatio = calculator.myDivision(variable.getMarketCapitalization(), variable.getEquityCapital()); // PBR = 시가총액/자기자본
        this.priceSalesRatio = calculator.myDivision(variable.getMarketCapitalization(), variable.getSales()); // PSR = 시가총액/매출액

        this.netWorthToTotalAsset = calculator.myRatio(variable.getEquityCapital(), variable.getTotalAssets());  // 자기자본비율 = 자기자본/총자본 * 100
        this.dependenceOnDebt = calculator.myRatio(calculator.myPlus(variable.getShortermAndLongtermBorrowings(), variable.getBons()) , variable.getTotalAssets());  // 차입금의존도 = (장단기 차입금 + 사채)/총자본 * 100
        this.averageInterestRateOnBorrowings = calculator.myRatioWithSum(variable.getInterestExpense(), variable.getShortermAndLongtermBorrowings(), variable.getBons());  // 차입금평균이자율 = 이자비용/(장단기차입금+사채)의 평잔 * 100
        this.timesInterestEarned = calculator.myDivision(variable.getNetProfit(), variable.getInterestExpense()); // 이자보상비율 = 영업이익/이자비용 > (영업이익/총자본)/(이자비용/차입금) = 총자본영업이익률/차입금평균이자율
        this.ebitaInterestExpense = calculator.myDivision(calculator.myPlus(netProfitBeforeTax, variable.getInterestExpense(), variable.getDepreciation()),variable.getInterestExpense()); // EBITDA/이자비용비율 = EBITDA/이자비용 = (세전순이익 + 이자비용 + 감가상각비 및 무형자산상각비) / 이자비용
        this.inventoryTurnover = calculator.myDivision(variable.getSales(), variable.getInventories()); // 재고자산회전율 = 매출액/재고자산(평잔)
        this.totalAssetTurnover = calculator.myDivision(variable.getSales(),variable.getTotalAssets()); // 총자산회전율 = 매출액/총자산(평잔)
        this.operatingProfitToSalesRatio = calculator.myRatio(variable.getNetProfit(), variable.getSales()); // 매출액영업이익률 = 영업이익/매출액 * 100
        this.totalAssetsOperatwingProfiRate = calculator.myDivision(variable.getTotalAssets(),variable.getNetProfit()); // 총자산영업이익률(ROA) = 총자산/영업이익
        this.equityTurnover = calculator.myDivision(variable.getSales(), variable.getEquityCapital()); // 자기자본회전율 = 매출액/자기자본(평잔)
        this.nonCurrentAssetTurnover = calculator.myDivision(variable.getSales(), variable.getNonCurrentAssets()); // 비유동자산회전율 = 매출액/비유동자산(평잔)
        this.receivablesTurnover = calculator.myDivision(variable.getSales(), variable.getTradeReceivables()); // 매출채권회전율 = 매출액/매출채권(평잔)
        this.payablesTurnover = calculator.myDivision(variable.getSales(),variable.getTradePayables()); // 매입채무회전율 = 매출액/매입채무(평잔)
        this.earningsAfterTaxToSalesRatio = calculator.myRatio(variable.getNetProfit(), variable.getSales()); // 매출액순이익율 = 당기순이익/매출액 * 100
        this.returnOnAssets = calculator.myRatio(variable.getNetProfit(), variable.getTotalAssets()); // 총자산순이익율 = 순이익/총자산 * 100
        this.cashFlowPerShare = calculator.myDivision(variable.getNetProfit(), variable.getNumberOfListedShares()); // 주당현금흐름 = 주주현금흐름 / 발행주식수 = (당기순이익 + 현금의 유출이 없는 비용 - 현금의 유입이 없는 수익) = 순이익 / 주식수
        this.priceCashFlowRatio = calculator.myDivision(variable.getClosingPrcie(),cashFlowPerShare);// 주가현금흐름비율 = 주가/주당현금흐름
        this.dividendSolvencyMultiple = calculator.myDivision(variable.getCashFlowFromOperatingActivities(), variable.getDividendPayment());// 배당지급능력배수 = 영업활동으로 인한 현금흐름 / 배당금
        this.cashFlowToDebtRatio = calculator.myRatio(variable.getCashFlowFromOperatingActivities(), variable.getShortermAndLongtermBorrowings());// 현금흐름 대 차입금 비율 = 영업활동으로 인한 현금흐름 / 차입금(평잔) * 100
        this.cashFlowToTotalDebtRatio = calculator.myRatio(variable.getCashFlowFromOperatingActivities(), variable.getTotalLiabilities());// 현금흐름 대 총부채 비율 = 영업활동으로 인한 현금흐름 / 총부채(평잔) * 100
        this.cashFlowInterestCoverageRatio = calculator.myRatio(calculator.myPlus(variable.getCashFlowFromOperatingActivities(), variable.getInterestExpense()), variable.getInterestExpense());// 현금흐름이자보상비율 = (영업활동으로 인한 현금흐름 + 이자비용) / 이자비용 * 100
        this.cashFlow = calculator.myPlus(variable.getCashFlowFromOperatingActivities(), variable.getCashFlowFromInvestingActivities(), variable.getCashFlowFromFinancingActivities()); // 현금흐름 = 영업활동으로 인한 현금흐름 + 재무활동으로 인한 현금흐름 + 투자활동으로 인한 현금흐름
        this.turnoverRatioOfTotalOperatingCapital = calculator.mySubtractionForNull(variable.getTotalAssets(), variable.getInvestmentAsset(), variable.getAssetsUnderConstruction()); // 경영자본 = 총자산 - 투자자산 - 건설중인자산 = 경영자본회전율
    }
}
