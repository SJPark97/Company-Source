package com.jobtang.sourcecompany.api.corp_detail.repository;

import com.jobtang.sourcecompany.api.analysis_result.entity.AnalysisResult;
import com.jobtang.sourcecompany.api.corp_detail.entity.CorpDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorpDetailRepository extends JpaRepository<CorpDetail, String> {
    CorpDetail findByCorpDetailId(String corpId);

    Page<CorpDetail> findALlByOrderBySalesDesc(Pageable pageable);

}
