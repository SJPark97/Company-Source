package com.jobtang.sourcecompany.api.induty_detail.repository;

import com.jobtang.sourcecompany.api.induty_detail.entity.IndutyDetail;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IndutyDetailRepository extends JpaRepository<IndutyDetail, String> {

    @Query("SELECT indutyDetail.indutyCode FROM IndutyDetail indutyDetail")
    List<String> findAllIndutyCodes();
    IndutyDetail findByIndutyCode(String indutyCord);
}

//    @Query("SELECT corp.corpId FROM Corp corp")
//    List<String> findAllCorpIds();
=======

public interface IndutyDetailRepository extends JpaRepository<IndutyDetail, String> {
    IndutyDetail findByIndutyCode(String indutyCord);
}
>>>>>>> dcd36873a727d1402c37c4c0deafe32f26e4f324
