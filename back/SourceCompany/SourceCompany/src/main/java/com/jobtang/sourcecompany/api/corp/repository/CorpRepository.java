package com.jobtang.sourcecompany.api.corp.repository;

import com.jobtang.sourcecompany.api.corp.entity.Corp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CorpRepository extends JpaRepository<Corp, String> {
    List<Corp> findByCorpNameContains(String value);
    Corp findByCorpId(String corpId);
    List<Corp> findAllByOrderByViewCountDesc();
}
