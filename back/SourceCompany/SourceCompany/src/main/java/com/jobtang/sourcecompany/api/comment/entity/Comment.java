package com.jobtang.sourcecompany.api.comment.entity;

import com.jobtang.sourcecompany.api.community.entity.Community;
import com.jobtang.sourcecompany.api.user.entity.User;
import com.jobtang.sourcecompany.util.BaseEntity;
import com.sun.istack.NotNull;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends BaseEntity {
  @Id
  @Column(name = "comment_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column(nullable = false)
  private String content;

  @NotNull
  @Column(nullable = false)
  private Long parent;

  @NotNull
  @Column(nullable = false)
  private Long commentGroup;


  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "community_id")
  private Community community;

  public void updateCommentGroup(){
    this.commentGroup = this.id;
  }


}
