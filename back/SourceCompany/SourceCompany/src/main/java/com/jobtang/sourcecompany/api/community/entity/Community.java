package com.jobtang.sourcecompany.api.community.entity;

import com.jobtang.sourcecompany.api.comment.entity.Comment;
import com.jobtang.sourcecompany.api.user.entity.User;
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
public class Community extends BaseEntity {
  @Id
  @Column(name = "community_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column(nullable = false)
  private String communityType;
  @NotNull
  @Column(nullable = false)
  private String title;
  @NotNull
  @Column(nullable = false)
  private String content;

  @NotNull
  @Column(nullable = false)
  private int yesterdayView;

  @NotNull
  @Column(nullable = false)
  private int totalView;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @JsonIgnore
  @OneToMany(mappedBy = "user" )
  private List<Comment> comments = new ArrayList<>();

}
