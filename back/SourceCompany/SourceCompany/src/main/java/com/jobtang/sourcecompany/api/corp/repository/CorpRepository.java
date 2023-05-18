package com.jobtang.sourcecompany.api.corp.repository;

import com.jobtang.sourcecompany.api.corp.entity.Corp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CorpRepository extends JpaRepository<Corp, String> {
    List<Corp> findByCorpNameContains(String value);
    List<Corp> findByCorpNameStartingWith(String value);
    Corp findByCorpId(String corpId);
    @Query("SELECT corp.corpId FROM Corp corp")
    List<String> findAllCorpIds();
    Page<Corp> findAllByOrderByCorpId(Pageable pageable);
    Page<Corp> findAllByOrderByYesterdayViewDesc(Pageable pageable);

    // 산업별 정렬
//    Page<Corp> findAllByIndutyCode(Pageable pageable, String indutyCode);
    @Query(value = "SELECT * FROM corp where induty_code=:code order by rand() limit 5", nativeQuery = true)
    List<Corp> findAllByIndutyCode(@Param(value = "code") String code);
}
