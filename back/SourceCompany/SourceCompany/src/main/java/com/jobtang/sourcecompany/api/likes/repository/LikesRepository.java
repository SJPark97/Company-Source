package com.jobtang.sourcecompany.api.likes.repository;

import com.jobtang.sourcecompany.api.likes.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {
  Optional<Likes> findByUserIdAndCommunityId (Long userId , Long communityId);
  Optional<Likes> findByIdAndIsActiveTrue(Long likesId);
}
