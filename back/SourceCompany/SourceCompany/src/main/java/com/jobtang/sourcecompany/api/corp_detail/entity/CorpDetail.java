package com.jobtang.sourcecompany.api.corp_detail.entity;

import com.jobtang.sourcecompany.api.corp.entity.Corp;
import com.jobtang.sourcecompany.util.BaseEntity;

import javax.persistence.*;

@Entity
public class CorpDetail extends BaseEntity {
    @Id
    @GeneratedValue
    private String corpDetailId;

    private int currentAsset; // 유동자산

    private int currentLiabilities; // 유동부채

    private int totalAssets; // 총자산

    private int retainedEarnings; // 이익잉여금

    private int netProfit; // 순이익(영업이익)

    private int cashAndCashEquivalents; // 현금 및 현금성 자산

    private int equityCapital; // 자기자본

    private int nonCurrentAssets; // 비유동자산

    private int nonCurrentLiabilities; // 비유동부채

    private int tangibleAssets; // 유형자산

    private int previousSales; // 전기매출액

    private int previousTotalAssets; // 전기총자산

    private int previousEquityCapital; // 전기자기자본

    private int previousNetProfit; // 전기순이익

    private int beforePreviousNetProfit; // 2년전 순이익

    private int sales; // 매출액

    private int totalLiabilities; // 총부채

    private int cashFlow; // 현금흐름

    private int OperatingFundExpenditure; // 영업자금지출

    private float tax; // 세금

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "corp_id")
    private Corp corp;


}
