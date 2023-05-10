package com.jobtang.sourcecompany.api.analysis_result.repository;

import com.jobtang.sourcecompany.api.analysis_result.entity.AnalysisResult;
import com.jobtang.sourcecompany.api.corp.entity.Corp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnalysisResultRepository extends JpaRepository<AnalysisResult, String> {
    AnalysisResult findByCorp(Corp corp);
}
