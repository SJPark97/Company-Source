package com.jobtang.sourcecompany.api.induty_detail.entity;

<<<<<<< HEAD
import com.jobtang.sourcecompany.api.corp.entity.Corp;
import com.jobtang.sourcecompany.api.corp_detail.util.variable.EntityVariable;
=======
>>>>>>> dcd36873a727d1402c37c4c0deafe32f26e4f324
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
<<<<<<< HEAD
public class IndutyDetail extends BaseEntity implements EntityVariable {
=======
public class IndutyDetail extends BaseEntity {
>>>>>>> dcd36873a727d1402c37c4c0deafe32f26e4f324
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

//    private Integer cashFlow; // 현금흐름

//    private Integer OperatingFundExpenditure; // 영업자금지출

    private Long tax; // 세금

    private Long inventories; // 재고자산

    private Double earningPerShare; // 주당이익(보통주)

    private Double previousEarningPerShare; // 전기주당이익(보통주)

    private Double capitalSurplus; // 자본잉여금

    private Long marketCapitalization; // 시가총액

    private Long numberOfListedShares; // 주식 수

    private Long closingPrcie; // 종가

    private Long interestExpense; // 이자비용
<<<<<<< HEAD

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
    public Double getEarningPerShare() {
        return earningPerShare;
    }

    @Override
    public Double getPreviousEarningPerShare() {
        return previousEarningPerShare;
    }

    @Override
    public Double getCapitalSurplus() {
        return capitalSurplus;
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
=======
>>>>>>> dcd36873a727d1402c37c4c0deafe32f26e4f324
}
