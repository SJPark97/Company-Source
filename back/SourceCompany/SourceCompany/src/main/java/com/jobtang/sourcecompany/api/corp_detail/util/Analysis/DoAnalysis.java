package com.jobtang.sourcecompany.api.corp_detail.util.Analysis;

import com.jobtang.sourcecompany.api.corp_detail.dto.AnalysisDto;
import com.jobtang.sourcecompany.api.corp_detail.dto.VariableDto;
import com.jobtang.sourcecompany.api.corp_detail.util.Analysis.analysis100.*;
import com.jobtang.sourcecompany.api.corp_detail.util.Analysis.analysis400.*;
import com.jobtang.sourcecompany.api.corp_detail.util.variable.AnalysisVariable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DoAnalysis {

    private final Analysis101 analysis101;
    private final Analysis103 analysis103;
    private final Analysis104 analysis104;
    private final Analysis109 analysis109;
    private final Analysis110 analysis110;
    private final Analysis111 analysis111;
    private final Analysis113 analysis113;

    private final Analysis405 analysis405;



    public VariableDto DoAnalysis(AnalysisVariable analysisVariable) {
        List<AnalysisDto> data = new ArrayList<>();

        data.add(analysis101.analysis(analysisVariable));
        data.add(analysis103.analysis(analysisVariable));
        data.add(analysis104.analysis(analysisVariable));
        data.add(analysis109.analysis(analysisVariable));
        data.add(analysis110.analysis(analysisVariable));
        data.add(analysis111.analysis(analysisVariable));
        data.add(analysis113.analysis(analysisVariable));
        data.add(analysis405.analysis(analysisVariable));
        return new VariableDto().builder()
                .variableName(analysisVariable.variableName)
                .variableId(analysisVariable.variableId)
                .data(data)
                .build();
    }
}
