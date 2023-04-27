package com.jobtang.sourcecompany.api.corp_detail.document;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.HashMap;

@Document(collection = "analysis")
public class analysis {
    @Id
    private String corpId;

    private HashMap<String, HashMap> data;

}
