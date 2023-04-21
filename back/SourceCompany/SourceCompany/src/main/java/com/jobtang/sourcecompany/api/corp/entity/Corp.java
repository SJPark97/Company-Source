package com.jobtang.sourcecompany.api.corp.entity;


import com.jobtang.sourcecompany.util.BaseEntity;
import com.sun.istack.NotNull;
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
public class Corp  extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private String corpId;

  @Column(nullable = false)
  private String corpName;

  @Column(nullable = true)
  private String corpImg;

  @Column(nullable = false)
  private Integer yesterdayView;

  @Column(nullable = false)
  private Integer totalView;

  //  분석용 컬럼
//  Dart
  @Column(nullable = true)
  private String netWorkingCapital; // 순운전자본

  @Column(nullable = true)
  private String totalAssets; // 총자산

  @Column(nullable = true)
  private String retainedEarnings; // 이익잉여금

  @Column(nullable = true)
  private String netProfit; // 순이익

  @Column(nullable = true)
  private String interestExpenses; // 이자비용

  @Column(nullable = true)
  private String tax;// 세금

  @Column(nullable = true)
  private String sales; // 매출액

  @Column(nullable = true)
  private String totalLiabilities; // 총부채

  //  주식 데이터
  @Column(nullable = true)
  private String marketCapitalization; // 시가총액
}
