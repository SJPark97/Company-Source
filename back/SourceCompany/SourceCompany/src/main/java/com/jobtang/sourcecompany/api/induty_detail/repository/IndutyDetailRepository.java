package com.jobtang.sourcecompany.api.induty_detail.repository;

import com.jobtang.sourcecompany.api.induty_detail.entity.IndutyDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IndutyDetailRepository extends JpaRepository<IndutyDetail, String> {

    @Query("SELECT indutyDetail.indutyCode FROM IndutyDetail indutyDetail")
    List<String> findAllIndutyCodes();
    IndutyDetail findByIndutyCode(String indutyCord);
}

//    @Query("SELECT corp.corpId FROM Corp corp")
//    List<String> findAllCorpIds();