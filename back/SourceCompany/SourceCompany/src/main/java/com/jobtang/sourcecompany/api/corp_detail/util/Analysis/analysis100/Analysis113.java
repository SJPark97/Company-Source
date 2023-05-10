package com.jobtang.sourcecompany.api.corp_detail.util.Analysis.analysis100;

import com.jobtang.sourcecompany.api.corp_detail.dto.AnalysisDto;
import com.jobtang.sourcecompany.api.corp_detail.dto.AnalysisResultDto;
import com.jobtang.sourcecompany.api.corp_detail.util.Analysis.AnalysisForm;
import com.jobtang.sourcecompany.api.corp_detail.util.variable.AnalysisVariable;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@NoArgsConstructor
public class Analysis113 implements AnalysisForm {

    // 변수 설정
    private Boolean isExistAll;

    private AnalysisVariable variable;

    private String analysisId = "113";
    private String analysisName = "ROI 분석";

    @Override
    public AnalysisDto analysis(AnalysisVariable variable) {
        log.info(analysisName+"("+analysisId+")" + "진입");
        this.variable = variable;
        this.isExistAll = true;

        checkNull();

        return setInfo(calculate());
    }

    @Override
    public void checkNull() {
        if (variable.returnOnInvestment == null || variable.returnOnEquity == null) {
            this.isExistAll = false;
        }
    }

    @Override
    public List<AnalysisResultDto> calculate() {

        List<AnalysisResultDto> result = new ArrayList<>();
        try {
            AnalysisResultDto analysisResultDto1 = new AnalysisResultDto().builder()
                    .name("ROI")
                    .value(variable.returnOnInvestment)
                    .build();
            analysisResultDto1.round();
            result.add(analysisResultDto1);

            AnalysisResultDto analysisResultDto2 = new AnalysisResultDto().builder()
                    .name("ROE")
                    .value(variable.returnOnEquity)
                    .build();
            analysisResultDto2.round();
            result.add(analysisResultDto2);

            return result;

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public AnalysisDto setInfo(List<AnalysisResultDto> data) {
        return new AnalysisDto().builder()
                .analysisId(analysisId)
                .analysisName(analysisName)
                .isExistAll(isExistAll)
                .analysisResult(data)
                .build();
    }
}
