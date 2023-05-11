package com.jobtang.sourcecompany.api.likes.entity;


import com.jobtang.sourcecompany.api.community.entity.Community;
import com.jobtang.sourcecompany.api.user.entity.User;
import com.jobtang.sourcecompany.util.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Likes extends BaseEntity {
  @Id
  @Column(name = "like_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "community_id")
  private Community community;


}
