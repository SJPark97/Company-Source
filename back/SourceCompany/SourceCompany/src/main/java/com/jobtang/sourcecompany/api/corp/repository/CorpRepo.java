package com.jobtang.sourcecompany.api.corp.repository;

import com.jobtang.sourcecompany.api.corp.entity.Corp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CorpRepo extends JpaRepository<Corp, String> {
    List<Corp> findByCorpNameContains(String value);
}
