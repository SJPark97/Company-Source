package com.jobtang.sourcecompany.api.induty.entity;

import com.jobtang.sourcecompany.api.corp_detail.entity.CorpDetail;
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
public class Induty  extends BaseEntity {
    @Id
    private String indutyCode; // 산업코드

    private Integer currentAsset; // 유동자산

    private Integer currentLiabilities; // 유동부채

    private Integer totalAssets; // 총자산

    private Integer retainedEarnings; // 이익잉여금

    private Integer netProfit; // 순이익(영업이익)

    private Integer cashAndCashEquivalents; // 현금 및 현금성 자산

    private Integer equityCapital; // 자기자본(자본금)

    private Integer nonCurrentAssets; // 비유동자산

    private Integer nonCurrentLiabilities; // 비유동부채

    private Integer tangibleAssets; // 유형자산

    private Integer previousSales; // 전기매출액

    private Integer previousTotalAssets; // 전기총자산

    private Integer previousEquityCapital; // 전기자기자본

    private Integer previousNetProfit; // 전기순이익

    private Integer beforePreviousNetProfit; // 2년전 순이익

    private Integer sales; // 매출액

    private Integer totalLiabilities; // 총부채

//    private Integer cashFlow; // 현금흐름

//    private Integer OperatingFundExpenditure; // 영업자금지출

    private Double tax; // 세금

    private Integer inventories; // 재고자산

    private Double earningPerShare; // 주당이익(보통주)

    private Double previousEarningPerShare; // 전기주당이익(보통주)

    private Double capitalSurplus; // 자본잉여금
}
