package com.jobtang.sourcecompany.api.corp_detail.util.Analysis.Analysis300;

import com.jobtang.sourcecompany.api.corp_detail.dto.AnalysisDto;
import com.jobtang.sourcecompany.api.corp_detail.dto.AnalysisResultDto;
import com.jobtang.sourcecompany.api.corp_detail.util.Analysis.AnalysisForm;
import com.jobtang.sourcecompany.api.corp_detail.util.variable.AnalysisVariable;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@NoArgsConstructor
public class Analysis304 implements AnalysisForm {

    // 변수 설정
    private Boolean isExistAll;

    private AnalysisVariable variable;

    private String analysisId = "304";
    private String analysisName = "현금흐름표 분석(현금흐름과 상환능력비율)";

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
        if (variable.cashFlowToDebtRatio == null
                || variable.cashFlowToTotalDebtRatio == null
                || variable.cashFlowInterestCoverageRatio == null
        ) {
            isExistAll = false;
        }
    }

    @Override
    public List<AnalysisResultDto> calculate() {
//      4) 현금흐름 대 차입금 비율 : 영업활동으로 인한 현금흐름 / 차입금(평잔) * 100
//      5) 현금흐름 대 총부채 비율 : 영업활동으로 인한 현금흐름 / 총부채(평잔) * 100
//      6) 현금흐름이자보상비율 : (영업활동으로 인한 현금흐름 + 이자비용) / 이자비용 * 100
        try {
            return new ArrayList<>(List.of(
                            new AnalysisResultDto().builder()
                                    .name("현금흐름 대 차입금 비율")
                                    .value(variable.cashFlowToDebtRatio)
                                    .build()

                            ,new AnalysisResultDto().builder()
                                    .name("현금흐름 대 총부채 비율")
                                    .value(variable.cashFlowToTotalDebtRatio)
                                    .build()

                            ,new AnalysisResultDto().builder()
                                    .name("현금흐름이자보상비율")
                                    .value(variable.cashFlowInterestCoverageRatio)
                                    .build()
                    ));
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
