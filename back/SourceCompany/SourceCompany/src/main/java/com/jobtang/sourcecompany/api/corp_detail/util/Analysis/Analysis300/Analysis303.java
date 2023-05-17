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
public class Analysis303 implements AnalysisForm {

    // 변수 설정
    private Boolean isExistAll;

    private AnalysisVariable variable;

    private String analysisId = "303";
    private String analysisName = "현금흐름표 분석(현금흐름과 주가비율)";

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
        if (variable.cashFlowPerShare == null
                || variable.priceCashFlowRatio == null
                || variable.dividendSolvencyMultiple == null
        ) {
            isExistAll = false;
        }
    }

    @Override
    public List<AnalysisResultDto> calculate() {
//      1) 주당현금흐름 : 주주현금흐름 / 발행주식수 = (당기순이익 + 현금의 유출이 없는 비용 - 현금의 유입이 없는 수익)
//      2) 주가현금흐름비율 : 주가/주당현금흐름
//      3) 배당지급능력배수 : 영업활동으로 인한 현금흐름 / 배당금
        try {
            return new ArrayList<>(List.of(
                            new AnalysisResultDto().builder()
                                    .name("주당현금흐름")
                                    .value(variable.cashFlowPerShare)
                                    .build()

                            ,new AnalysisResultDto().builder()
                                    .name("주가현금흐름비율")
                                    .value(variable.priceCashFlowRatio)
                                    .build()

                            ,new AnalysisResultDto().builder()
                                    .name("배당지급능력배수")
                                    .value(variable.dividendSolvencyMultiple)
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
