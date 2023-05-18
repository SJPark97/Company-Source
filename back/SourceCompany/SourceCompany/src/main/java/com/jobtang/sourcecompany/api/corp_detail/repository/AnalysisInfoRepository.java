package com.jobtang.sourcecompany.api.corp_detail.repository;

import com.jobtang.sourcecompany.api.corp_detail.document.AnalysisInfoDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnalysisInfoRepository extends MongoRepository<AnalysisInfoDocument, String> {
    AnalysisInfoDocument findByAnalysisId(String analysisId);
}
