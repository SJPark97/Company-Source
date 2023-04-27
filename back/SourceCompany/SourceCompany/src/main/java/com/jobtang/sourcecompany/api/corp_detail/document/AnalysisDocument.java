package com.jobtang.sourcecompany.api.corp_detail.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.HashMap;

@Setter
@Getter
@Document(collection = "analysis")
public class AnalysisDocument {
    @Id
    private String corpId;

    private HashMap<String, HashMap> data;

}
