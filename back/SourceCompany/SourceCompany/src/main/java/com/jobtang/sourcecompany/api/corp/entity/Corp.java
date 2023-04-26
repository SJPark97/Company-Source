package com.jobtang.sourcecompany.api.corp.entity;


import com.jobtang.sourcecompany.api.corp_detail.entity.CorpDetail;
import com.jobtang.sourcecompany.util.BaseEntity;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Corp  extends BaseEntity {
  @Id
  @GeneratedValue
  private String corpId;

  private String stockId;

  @Column(nullable = false)
  private String corpName;

  @Column(nullable = true)
  private String corpImg;

  @Column(nullable = false)
  private Integer yesterdayView;

  @Column(nullable = false)
  private Integer totalView;

  @OneToOne(mappedBy = "corp", cascade = CascadeType.ALL, orphanRemoval = true)
  private CorpDetail corpDetail;
}
