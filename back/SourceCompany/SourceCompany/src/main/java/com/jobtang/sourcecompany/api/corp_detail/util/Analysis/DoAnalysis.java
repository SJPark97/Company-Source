package com.jobtang.sourcecompany.api.corp_detail.util.Analysis;

import com.jobtang.sourcecompany.api.corp_detail.dto.VariableDto;
import com.jobtang.sourcecompany.api.corp_detail.util.Analysis.Analysis300.Analysis303;
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

    //버전 2
    private final Analysis102 analysis102;
    private final Analysis105 analysis105;
    private final Analysis106 analysis106;
    private final Analysis108 analysis108;
    private final Analysis114 analysis114;
    private final Analysis303 analysis303;
    private final Analysis408 analysis408;


    public VariableDto DoAnalysis(AnalysisVariable analysisVariable) {
        return new VariableDto().builder()
                .variableName(analysisVariable.variableName)
                .variableId(analysisVariable.variableId)
                .data(new ArrayList<>(List.of(
                        analysis101.analysis(analysisVariable)
                        ,analysis103.analysis(analysisVariable)
                        ,analysis104.analysis(analysisVariable)
                        ,analysis109.analysis(analysisVariable)
                        ,analysis110.analysis(analysisVariable)
                        ,analysis111.analysis(analysisVariable)
                        ,analysis113.analysis(analysisVariable)
                        ,analysis405.analysis(analysisVariable)

                        // 버전2
                        ,analysis102.analysis(analysisVariable)
                        ,analysis105.analysis(analysisVariable)
                        ,analysis106.analysis(analysisVariable)
                        ,analysis108.analysis(analysisVariable)
                        ,analysis114.analysis(analysisVariable)
                        ,analysis303.analysis(analysisVariable)
                        ,analysis408.analysis(analysisVariable)
                )))
                .build();
    }
}
