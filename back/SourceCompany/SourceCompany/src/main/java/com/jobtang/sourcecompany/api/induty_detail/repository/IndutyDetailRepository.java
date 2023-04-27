package com.jobtang.sourcecompany.api.induty_detail.repository;

import com.jobtang.sourcecompany.api.induty_detail.entity.IndutyDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndutyDetailRepository extends JpaRepository<IndutyDetail, String> {
    IndutyDetail findByIndutyCode(String indutyCord);
}
