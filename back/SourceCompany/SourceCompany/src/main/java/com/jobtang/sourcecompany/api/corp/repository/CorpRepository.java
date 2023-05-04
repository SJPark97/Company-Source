package com.jobtang.sourcecompany.api.corp.repository;

import com.jobtang.sourcecompany.api.corp.entity.Corp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CorpRepository extends JpaRepository<Corp, String> {
    List<Corp> findByCorpNameContains(String value);
    Corp findByCorpId(String corpId);
    @Query("SELECT corp.corpId FROM Corp corp")
    List<String> findAllCorpIds();

    Page<Corp> findAllByOrderByYesterdayViewDesc(Pageable pageable);

}
