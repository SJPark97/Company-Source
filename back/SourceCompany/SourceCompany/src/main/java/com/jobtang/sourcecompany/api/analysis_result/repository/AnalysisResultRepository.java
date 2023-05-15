package com.jobtang.sourcecompany.api.analysis_result.repository;

import com.jobtang.sourcecompany.api.analysis_result.entity.AnalysisResult;
import com.jobtang.sourcecompany.api.corp.entity.Corp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnalysisResultRepository extends JpaRepository<AnalysisResult, String> {
    AnalysisResult findByCorp(Corp corp);

    Page<AnalysisResult> findALlByResult101(Pageable pageable, String result);
    Page<AnalysisResult> findALlByResult103(Pageable pageable, String result);
    Page<AnalysisResult> findALlByResult104(Pageable pageable, String result);
    Page<AnalysisResult> findALlByResult109(Pageable pageable, String result);
    Page<AnalysisResult> findALlByResult110(Pageable pageable, String result);
    Page<AnalysisResult> findALlByResult111(Pageable pageable, String result);
    Page<AnalysisResult> findALlByResult113(Pageable pageable, String result);
    Page<AnalysisResult> findALlByResult405(Pageable pageable, String result);
}
