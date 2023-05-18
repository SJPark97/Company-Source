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
public class Analysis111 implements AnalysisForm {

    // 변수 설정
    private Boolean isExistAll;

    private AnalysisVariable variable;

    private String analysisId = "111";
    private String analysisName = "주가매출액비율";

    @Override
    public void checkNull() {
        if (variable.priceSalesRatio == null) {
            isExistAll = false;
        }
    }

    @Override
    public AnalysisDto analysis(AnalysisVariable variable) {
        log.info(analysisName+"("+analysisId+")" + "진입");
        this.variable = variable;
        this.isExistAll = true;

        checkNull();

        return setInfo(calculate());
    }

    @Override
    public List<AnalysisResultDto> calculate() {

        List<AnalysisResultDto> result = new ArrayList<>();
        try {
            AnalysisResultDto analysisResultDto1 = new AnalysisResultDto().builder()
                    .name("주가매출액비율 (PSR)")
                    .value(variable.priceSalesRatio)
                    .build();
            analysisResultDto1.round();
            result.add(analysisResultDto1);

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
