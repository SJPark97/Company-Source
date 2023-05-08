package com.jobtang.sourcecompany.api.community.repository;

import com.jobtang.sourcecompany.api.community.entity.Community;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommunityRepository  extends JpaRepository<Community , Long> {

  // 삭제 되지 않은 값들만 검색

  //삭제 되지 않은 값들만 검색
  Page<Community> findAllByIsActiveTrueAndCommunityType (boolean isActive , String communityType , Pageable pageable);

  Page<Community> findAllByContentAndIsActiveTrue (String content , Pageable pageable);

  Page<Community> findAllByTitleAndIsActiveTrue (String type , Pageable pageable);

}
