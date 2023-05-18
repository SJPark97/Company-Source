package com.jobtang.sourcecompany.api.community.repository;

import com.jobtang.sourcecompany.api.community.entity.Community;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommunityRepository  extends JpaRepository<Community , Long> {

  // 삭제 되지 않은 값들만 검색
  Optional<Community> findByIdAndIsActiveTrue(Long communityId);

  Page<Community> findAllByContentContainingAndIsActiveTrue(String content, Pageable pageable);
  Page<Community> findAllByTitleContainingAndIsActiveTrue(String content, Pageable pageable);
  //삭제 되지 않은 값들만 검색
  Page<Community> findAllByIsActiveAndCommunityType (boolean isActive , String communityType , Pageable pageable);

  Page<Community> findAllByCommunityTypeAndContentContainingAndIsActiveTrue (String communityType,String content , Pageable pageable);

  Page<Community> findAllByCommunityTypeAndTitleContainingAndIsActiveTrue (String communityType , String type , Pageable pageable);

  // randing 가져올때 사용
  // corpHot, freeHot 가져오는 메소드
//  @Query(nativeQuery = true ,value = "SELECT c.* \n" +
//          "FROM community c, (\n" +
//          "select community_id , count(*) as l_cnt\n" +
//          "from likes\n" +
//          "group by community_id\n" +
//          "order by l_cnt DESC\n" +
//          ") l\n" +
//          "where  community_type = :communityType " +
//          "and c.community_id = l.community_id\n" +
//          "order by l_cnt DESC\n" +
//          "limit :n" )
  List<Community> findByCommunityTypeAndIsActiveTrueAndLikesCntGreaterThanEqualOrderByCreatedDateDesc(String communityType ,int likesCnt ,Pageable pageable);

}
