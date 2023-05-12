package com.jobtang.sourcecompany.api.community_image.entity;

import com.jobtang.sourcecompany.api.community.entity.Community;
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
public class CommunityImageEntity extends BaseEntity {
    @Id
    @Column(name = "community_image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String communityImageUrl;
}
