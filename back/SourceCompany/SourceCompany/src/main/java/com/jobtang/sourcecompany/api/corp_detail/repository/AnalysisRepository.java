package com.jobtang.sourcecompany.api.corp_detail.repository;

import com.jobtang.sourcecompany.api.corp_detail.document.AnalysisDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AnalysisRepository extends MongoRepository<AnalysisDocument, String> {
    AnalysisDocument findByCorpId(String corpId);
    List<AnalysisDocument> findAll();
}
