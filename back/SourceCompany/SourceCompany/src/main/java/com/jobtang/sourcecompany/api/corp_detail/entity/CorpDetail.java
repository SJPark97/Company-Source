package com.jobtang.sourcecompany.api.corp_detail.entity;

import com.jobtang.sourcecompany.api.corp.entity.Corp;
import com.jobtang.sourcecompany.util.BaseEntity;
import lombok.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CorpDetail extends BaseEntity {
    @Id
    private String corpDetailId;

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

    private Double tax; // 세금

    private Long inventories; // 재고자산

    private Double earningPerShare; // 주당이익(보통주)

    private Double previousEarningPerShare; // 전기주당이익(보통주)

    private Double capitalSurplus; // 자본잉여금

    private Long marketCapitalization; // 시가총액

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "corp_id")
    private Corp corp;


}
