package com.jobtang.sourcecompany.api.corp_detail.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Setter
@Getter
@Document(collection = "analysisGpt")
public class AnalysisGptDocument {
    @Id
    private String corpId;

    private String corpName;

    private String content;
}
