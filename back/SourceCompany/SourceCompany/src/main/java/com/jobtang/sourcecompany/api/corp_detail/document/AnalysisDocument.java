package com.jobtang.sourcecompany.api.corp_detail.document;

<<<<<<< HEAD
import com.jobtang.sourcecompany.api.corp_detail.dto.AnalysisDto;
import com.jobtang.sourcecompany.api.corp_detail.dto.VariableDto;
=======
>>>>>>> dcd36873a727d1402c37c4c0deafe32f26e4f324
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
<<<<<<< HEAD
import java.util.List;
=======
import java.util.HashMap;
>>>>>>> dcd36873a727d1402c37c4c0deafe32f26e4f324

@Setter
@Getter
@Document(collection = "analysis")
public class AnalysisDocument {
    @Id
<<<<<<< HEAD
    private String variableId;

    private String variableName;

    private List<AnalysisDto> data;
=======
    private String corpId;

    private HashMap<String, HashMap> data;
>>>>>>> dcd36873a727d1402c37c4c0deafe32f26e4f324

}
