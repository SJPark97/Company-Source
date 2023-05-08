package com.jobtang.sourcecompany.api.community.repository;

import com.jobtang.sourcecompany.api.community.entity.Community;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository  extends JpaRepository<Community , Long> {
  Page<Community> findAll (Pageable pageable);

}
