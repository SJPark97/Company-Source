package com.jobtang.sourcecompany.api.user.entity;

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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseEntity implements UserDetails  {
  @Id
  @Column(name = "user_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotNull
  @Column(nullable = false)
//  @ElementCollection
//  private List<Role> role;
//  private List<String> role;
  private String role;

  @NotNull
  @Column(nullable = false)
  private String nickname;

  @NotNull
  @Column(nullable = true)
  private String email;

  @NotNull
  @Column(nullable = false)
  private String password;

  private String userImg;


  @Column(nullable = false)
  private String sex;


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

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Collection<GrantedAuthority> authorities = new ArrayList<>();

    for(String role : role.split(",")){
      authorities.add(new SimpleGrantedAuthority(role));
    }
//    for(String r:role) {
//      authorities.add(new SimpleGrantedAuthority(r));
//    }
    return authorities;
  }
  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
