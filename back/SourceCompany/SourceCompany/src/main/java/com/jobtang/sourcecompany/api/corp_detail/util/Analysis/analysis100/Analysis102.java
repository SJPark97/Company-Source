package com.jobtang.sourcecompany.api.corp_detail.util.Analysis.analysis100;

import com.jobtang.sourcecompany.api.corp_detail.dto.AnalysisDto;
import com.jobtang.sourcecompany.api.corp_detail.dto.AnalysisResultDto;
import com.jobtang.sourcecompany.api.corp_detail.util.Analysis.AnalysisForm;
import com.jobtang.sourcecompany.api.corp_detail.util.Calculator;
import com.jobtang.sourcecompany.api.corp_detail.util.variable.AnalysisVariable;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@NoArgsConstructor
public class Analysis102 implements AnalysisForm {

    // 변수 설정
    private Boolean isExistAll;

    private AnalysisVariable variable;

    private Calculator calculator;

    private String analysisId = "102";
    private String analysisName = "레버리지 분석";

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
        if (variable.debtToEquityRatio == null
                || variable.netWorthToTotalAsset == null
                || variable.dependenceOnDebt == null
                || variable.averageInterestRateOnBorrowings == null
                || variable.timesInterestEarned == null
                || variable.ebitaInterestExpense == null
        ) {
            isExistAll = false;
        }
    }

    @Override
    public List<AnalysisResultDto> calculate() {
//      1. 부채비율 = 부채/자기자본 * 100
//      2. 자기자본비율 = 자기자본/총자본 * 100
//      3. 차입금의존도 = (장단기 차입금 + 사채)/총자본 * 100
//      4. 차입금평균이자율 = 이자비용/(장단기차입금+사채)의 평잔 * 100
//      5. 이자보상비율 = 영업이익/이자비용 > (영업이익/총자본)/(이자비용/차입금) = 총자본영업이익률/차입금평균이자율
//      6. EBITDA/이자비용비율 = EBITDA/이자비용 = (세전순이익 + 이자비용 + 감가상각비 및 무형자산상각비) / 이자비용
        try {
            return new ArrayList<>(List.of(
                            new AnalysisResultDto().builder()
                                    .name("부채비율")
                                    .value(variable.debtToEquityRatio)
                                    .build(),

                            new AnalysisResultDto().builder()
                                    .name("자기자본비율")
                                    .value(variable.netWorthToTotalAsset)
                                    .build(),

                            new AnalysisResultDto().builder()
                                    .name("차입금의존도")
                                    .value(variable.dependenceOnDebt)
                                    .build(),

                            new AnalysisResultDto().builder()
                                    .name("차입금평균이자율")
                                    .value(variable.averageInterestRateOnBorrowings)
                                    .build(),

                            new AnalysisResultDto().builder()
                                    .name("이자보상비율")
                                    .value(variable.timesInterestEarned)
                                    .build(),

                            new AnalysisResultDto().builder()
                                    .name("EBITDA/이자비용비율")
                                    .value(variable.ebitaInterestExpense)
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
