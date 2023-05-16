package com.jobtang.sourcecompany.api.induty_detail.entity;

import com.jobtang.sourcecompany.api.corp_detail.util.variable.EntityVariable;
import com.jobtang.sourcecompany.util.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IndutyDetail extends BaseEntity implements EntityVariable {
    @Id
    private String indutyCode; // 산업코드

    private String indutyName;  // 산업명

    private Long currentAsset; // 유동자산

    private Long currentLiabilities; // 유동부채

    private Long totalAssets; // 총자산

    private Long retainedEarnings; // 이익잉여금

    private Long netProfit; // 순이익(영업이익)

    private Long cashAndCashEquivalents; // 현금 및 현금성 자산

    private Long equityCapital; // 자기자본(자본금)

    private Long nonCurrentAssets; // 비유동자산

    private Long nonCurrentLiabilities; // 비유동부채

    private Long tangibleAssets; // 유형자산

    private Long previousSales; // 전기매출액

    private Long previousTotalAssets; // 전기총자산

    private Long previousEquityCapital; // 전기자기자본

    private Long previousNetProfit; // 전기순이익

    private Long beforePreviousNetProfit; // 2년전 순이익

    private Long sales; // 매출액

    private Long totalLiabilities; // 총부채

    private Long tax; // 세금

    private Long inventories; // 재고자산

    private Long marketCapitalization; // 시가총액

    private Long numberOfListedShares; // 주식 수

    private Long closingPrcie; // 종가

    private Long interestExpense; // 이자비용

    // 버전2
    private Long previousmarketCapitalization; // 전기 시가총액

    private Long previousNumberOfListedShares; // 전기 주식 수

    private Long bons;  // 사채

    private Long shortermAndLongtermBorrowings; //장단기 차입금

    private Long depreciation; //감가상각비

    private Long tradeReceivables; // 매출채권

    private Long tradePayables; // 매입채무

    private Long assetsUnderConstruction; // 건설중인자산

    private Long investmentAsset; // 투자자산

    private Long cashFlowFromOperatingActivities; // 영업활동으로인한 현금흐름

    private Long cashFlowFromInvestingActivities; // 투자활동으로인한 현금흐름

    private Long cashFlowFromFinancingActivities; // 재무활동으로인한 현금흐름

    private Long dividendPayment; // 배당금

    @Override
    public String getVariableId() {
        return indutyCode;
    }

    @Override
    public String getVariableName() {
        return indutyName;
    }

    @Override
    public Long getCurrentAsset() {
        return currentAsset;
    }

    @Override
    public Long getCurrentLiabilities() {
        return currentLiabilities;
    }

    @Override
    public Long getTotalAssets() {
        return totalAssets;
    }

    @Override
    public Long getRetainedEarnings() {
        return retainedEarnings;
    }

    @Override
    public Long getNetProfit() {
        return netProfit;
    }

    @Override
    public Long getCashAndCashEquivalents() {
        return cashAndCashEquivalents;
    }

    @Override
    public Long getEquityCapital() {
        return equityCapital;
    }

    @Override
    public Long getNonCurrentAssets() {
        return nonCurrentAssets;
    }

    @Override
    public Long getNonCurrentLiabilities() {
        return nonCurrentLiabilities;
    }

    @Override
    public Long getTangibleAssets() {
        return tangibleAssets;
    }

    @Override
    public Long getPreviousSales() {
        return previousSales;
    }

    @Override
    public Long getPreviousTotalAssets() {
        return previousTotalAssets;
    }

    @Override
    public Long getPreviousEquityCapital() {
        return previousEquityCapital;
    }

    @Override
    public Long getPreviousNetProfit() {
        return previousNetProfit;
    }

    @Override
    public Long getBeforePreviousNetProfit() {
        return beforePreviousNetProfit;
    }

    @Override
    public Long getSales() {
        return sales;
    }

    @Override
    public Long getTotalLiabilities() {
        return totalLiabilities;
    }

    @Override
    public Long getTax() {
        return tax;
    }

    @Override
    public Long getInventories() {
        return inventories;
    }

    @Override
    public Long getMarketCapitalization() {
        return marketCapitalization;
    }

    @Override
    public Long getNumberOfListedShares() {
        return numberOfListedShares;
    }

    @Override
    public Long getClosingPrcie() {
        return closingPrcie;
    }

    @Override
    public Long getInterestExpense() {
        return interestExpense;
    }

    // 버전 2
    @Override
    public Long getPreviousNumberOfListedShares() { return previousNumberOfListedShares; }

    @Override
    public Long getPreviousmarketCapitalization() { return previousmarketCapitalization; } // 전기 시가총액

    @Override
    public Long getBons() { return bons; };  // 사채

    @Override
    public Long getShortermAndLongtermBorrowings() { return shortermAndLongtermBorrowings; } //장단기 차입금

    @Override
    public Long getDepreciation() { return depreciation; } //감가상각비

    @Override
    public Long getTradeReceivables() { return tradeReceivables; } // 매출채권

    @Override
    public Long getTradePayables() { return tradePayables; } // 매입채무

    @Override
    public Long getAssetsUnderConstruction() { return assetsUnderConstruction; } // 건설중인 자산

    @Override
    public Long getInvestmentAsset() { return investmentAsset; } // 투자자산

    @Override
    public Long getCashFlowFromOperatingActivities() { return cashFlowFromOperatingActivities; } // 영업활동으로인한 현금흐름

    @Override
    public Long getCashFlowFromInvestingActivities() { return cashFlowFromInvestingActivities; } // 투자활동으로인한 현금흐름

    @Override
    public Long getCashFlowFromFinancingActivities() { return cashFlowFromFinancingActivities; } // 재무활동으로인한 현금흐름

    @Override
    public Long getDividendPayment() { return dividendPayment; } // 배당금
}
