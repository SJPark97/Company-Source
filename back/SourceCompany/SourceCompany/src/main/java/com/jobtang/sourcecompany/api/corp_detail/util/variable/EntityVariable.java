package com.jobtang.sourcecompany.api.corp_detail.util.variable;

public interface EntityVariable {
    String getVariableId();  // 회사나 산업 코드
    
    String getVariableName(); // 회사나 산업 이름
    
    Long getCurrentAsset(); // 유동자산

    Long getCurrentLiabilities(); // 유동부채

    Long getTotalAssets(); // 총자산

    Long getRetainedEarnings(); // 이익잉여금

    Long getNetProfit(); // 순이익(영업이익)

    Long getCashAndCashEquivalents(); // 현금 및 현금성 자산

    Long getEquityCapital(); // 자기자본(자본금)

    Long getNonCurrentAssets(); // 비유동자산

    Long getNonCurrentLiabilities(); // 비유동부채

    Long getTangibleAssets(); // 유형자산

    Long getPreviousSales(); // 전기매출액

    Long getPreviousTotalAssets(); // 전기총자산

    Long getPreviousEquityCapital(); // 전기자기자본

    Long getPreviousNetProfit(); // 전기순이익

    Long getBeforePreviousNetProfit(); // 2년전 순이익

    Long getSales(); // 매출액

    Long getTotalLiabilities(); // 총부채

    Long getTax(); // 세금

    Long getInventories(); // 재고자산

    Long getMarketCapitalization(); // 시가총액

    Long getNumberOfListedShares(); // 주식 수

    Long getClosingPrcie(); // 종가

    Long getInterestExpense(); // 이자비용

    // 버전2
    Long getPreviousNumberOfListedShares(); // 전기 주식 수

    Long getPreviousmarketCapitalization(); // 전기 시가총액

    Long getBons();  // 사채

    Long getShortermAndLongtermBorrowings(); //장단기 차입금

    Long getDepreciation(); //감가상각비

    Long getTradeReceivables(); // 매출채권

    Long getTradePayables(); // 매입채무

    Long getAssetsUnderConstruction(); // 건설중인 자산

    Long getInvestmentAsset(); // 투자자산

    Long getCashFlowFromOperatingActivities(); // 영업활동으로인한 현금흐름

    Long getCashFlowFromInvestingActivities(); // 투자활동으로인한 현금흐름

    Long getCashFlowFromFinancingActivities(); // 재무활동으로인한 현금흐름

    Long getDividendPayment(); // 배당금

}
