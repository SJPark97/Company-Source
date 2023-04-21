package com.jobtang.sourcecompany.api.inquiry.entity;

import com.jobtang.sourcecompany.api.inquiry_comment.entity.InquiryComment;
import com.jobtang.sourcecompany.api.user.dto.User;
import com.jobtang.sourcecompany.util.BaseEntity;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Inquiry extends BaseEntity {
  @Id
  @Column(name = "inquiry_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column(nullable = false)
  private String title;


  @NotNull
  @Column(nullable = false)
  private String content;


  @NotNull
  @Column(nullable = false)
  private boolean isLock;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @JsonIgnore
  @OneToMany(mappedBy = "inquiry" )
  private List<InquiryComment> inquiryComments= new ArrayList<>();
}
