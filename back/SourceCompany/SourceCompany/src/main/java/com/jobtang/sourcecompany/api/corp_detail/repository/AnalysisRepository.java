package com.jobtang.sourcecompany.api.corp_detail.repository;

import com.jobtang.sourcecompany.api.corp_detail.document.AnalysisDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AnalysisRepository extends MongoRepository<AnalysisDocument, String> {
<<<<<<< HEAD
    AnalysisDocument findByVariableId(String variableId);
=======
    AnalysisDocument findByCorpId(String corpId);
>>>>>>> dcd36873a727d1402c37c4c0deafe32f26e4f324
    List<AnalysisDocument> findAll();
}
