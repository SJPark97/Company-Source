package com.jobtang.sourcecompany.api.user.dto;

import com.jobtang.sourcecompany.api.comment.entity.Comment;
import com.jobtang.sourcecompany.api.community.entity.Community;
import com.jobtang.sourcecompany.api.faq.entity.Faq;
import com.jobtang.sourcecompany.api.inquiry.entity.Inquiry;
import com.jobtang.sourcecompany.api.inquiry_comment.entity.InquiryComment;
import com.jobtang.sourcecompany.api.scrap.entity.Scrap;
import com.jobtang.sourcecompany.util.BaseEntity;
import com.sun.istack.NotNull;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseEntity {
  @Id
  @Column(name = "user_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @NotNull
  @Column(nullable = false)
  private Role role;

  @NotNull
  @Column(nullable = false)
  private String nickname;

  @NotNull
  @Column(nullable = true)
  private String email;
  @NotNull
  @Column(nullable = false)
  private String username;
  @NotNull
  @Column(nullable = false)
  private String password;

  private String userImg;


  @Column(nullable = false)
  private String  sex;


  @Column(nullable = false)
  private LocalDate birth;

  @JsonIgnore
  @OneToMany(mappedBy = "user" )
  private List<Scrap> scraps = new ArrayList<>();

  @JsonIgnore
  @OneToMany(mappedBy = "user" )
  private List<Community> communities = new ArrayList<>();

  @JsonIgnore
  @OneToMany(mappedBy = "user" )
  private List<Comment> comments = new ArrayList<>();
  @JsonIgnore
  @OneToMany(mappedBy = "user" )
  private List<Inquiry> inquiries = new ArrayList<>();

  @JsonIgnore
  @OneToMany(mappedBy = "user" )
  private List<InquiryComment> inquiryComments = new ArrayList<>();

  @JsonIgnore
  @OneToMany(mappedBy = "user" )
  private List<Faq> faqs = new ArrayList<>();
}
