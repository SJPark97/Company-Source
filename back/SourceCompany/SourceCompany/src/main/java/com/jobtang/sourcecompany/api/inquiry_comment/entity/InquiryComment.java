package com.jobtang.sourcecompany.api.inquiry_comment.entity;

import com.jobtang.sourcecompany.api.inquiry.entity.Inquiry;
import com.jobtang.sourcecompany.api.user.dto.User;
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
public class InquiryComment extends BaseEntity {
  @Id
  @Column(name = "inquiry_comment_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column(nullable = false)
  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "inquiry_id")
  private Inquiry inquiry;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

}
