package com.jobtang.sourcecompany.api.corp_detail.document;

import com.jobtang.sourcecompany.api.corp_detail.dto.AnalysisDto;
import com.jobtang.sourcecompany.api.corp_detail.dto.VariableDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Setter
@Getter
@Document(collection = "analysis")
public class AnalysisDocument {
    @Id
    private String variableId;

    private String variableName;

    private List<AnalysisDto> data;

}
