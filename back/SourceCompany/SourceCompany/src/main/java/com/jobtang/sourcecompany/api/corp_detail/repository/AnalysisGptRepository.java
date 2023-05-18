package com.jobtang.sourcecompany.api.corp_detail.repository;

import com.jobtang.sourcecompany.api.corp_detail.document.AnalysisDocument;
import com.jobtang.sourcecompany.api.corp_detail.document.AnalysisGptDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AnalysisGptRepository extends MongoRepository<AnalysisGptDocument, String> {
    Optional<AnalysisGptDocument> findByCorpId(String corpId);
}
