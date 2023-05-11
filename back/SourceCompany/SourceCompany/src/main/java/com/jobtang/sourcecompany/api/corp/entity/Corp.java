package com.jobtang.sourcecompany.api.corp.entity;


import com.jobtang.sourcecompany.api.analysis_result.entity.AnalysisResult;
import com.jobtang.sourcecompany.api.corp_detail.entity.CorpDetail;
import com.jobtang.sourcecompany.util.BaseEntity;
import com.sun.istack.NotNull;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Corp  extends BaseEntity {
  @Id
  private String corpId;  // 회사 코드

  private String stockId;  // 회사 주식 코드

  @Column(nullable = false)
  private String corpName;  // 회사명

  private String corpImg;  // 로고 이미지

  private String corpSize; // 회사 규모 ( 대기업 , 중소기업 , 중견기업 ...)
//  @Column(nullable = false)
  private String indutyCode; // 산업코드

  private String indutyName; // 산업명

  private String homepage; // 홈페이지주소

  private int employees; // 사원수

  private LocalDateTime foundationDate; // 설립일

  private String address; // 회사주소

  @Column(nullable = false)
  private int yesterdayView;

  @Column(nullable = false)
  private int totalView;

  @JsonIgnore
  @OneToOne(mappedBy = "corp", cascade = CascadeType.ALL, orphanRemoval = true)
  private CorpDetail corpDetail;

  @JsonIgnore
  @OneToOne(mappedBy = "corp", cascade = CascadeType.ALL, orphanRemoval = true)
  private AnalysisResult analysisResult;

  public void updateViewCnt(Integer todayViewCnt) {
    this.totalView += yesterdayView;
    this.yesterdayView = todayViewCnt;
  }
}
