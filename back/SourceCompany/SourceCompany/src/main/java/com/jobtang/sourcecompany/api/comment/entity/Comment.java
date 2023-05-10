package com.jobtang.sourcecompany.api.comment.entity;

import com.jobtang.sourcecompany.api.community.entity.Community;
import com.jobtang.sourcecompany.api.user.entity.User;
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

<<<<<<< HEAD
  public void updateCommentGroup(){
    this.commentGroup = this.id;
  }
=======
>>>>>>> dcd36873a727d1402c37c4c0deafe32f26e4f324


}
