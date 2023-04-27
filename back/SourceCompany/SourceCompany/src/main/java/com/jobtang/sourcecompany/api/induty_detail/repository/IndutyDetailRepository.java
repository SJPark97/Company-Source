package com.jobtang.sourcecompany.api.induty.repository;

import com.jobtang.sourcecompany.api.induty.entity.Induty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndutyRepository extends JpaRepository<Induty, String> {
    Induty findByIndutyCode(String indutyCord);
}
