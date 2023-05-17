package com.jobtang.sourcecompany.api.corp_detail.util.Analysis.analysis100;

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
public class Analysis108 implements AnalysisForm {

    // 변수 설정
    private Boolean isExistAll;

    private AnalysisVariable variable;

    private String analysisId = "108";
    private String analysisName = "성장성 분석";

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
        if (variable.salesGrowthRate == null
                || variable.totalAssetGrowthRate == null
                || variable.netWorthGrowthRate == null
                || variable.netProfitGrowthRate == null
                || variable.earningsPerShareGrowthRate == null
                || variable.sustainableGrowthRate == null
        ) {
            isExistAll = false;
        }
    }

    @Override
    public List<AnalysisResultDto> calculate() {
//      1. 매출액증가율 = (당기 매출액 - 전기 매출액) / 전기 매출액 * 100
//      2. 총자산증가율 = (당기말 총자산- 전기말 총자산) / 전기말 매출액 * 100
//      3. 자기자본증가율 = (당기말 자기자본- 전기말 자기자본) / 전기말 자기자본 * 100
//      4. 순이익증가율 = (당기 순이익 - 전기 순이익) / 전기 순이익 * 100
//      5. 주당이익증가율 = (당기 주당이익- 전기 주당이익) / 전기 주당이익 * 100
//      6. 지속가능성장률 = 유보율 * 자기자본순이익률(ROE)
        try {
            return new ArrayList<>(List.of(
                            new AnalysisResultDto().builder()
                                    .name("매출액증가율")
                                    .value(variable.salesGrowthRate)
                                    .build(),

                            new AnalysisResultDto().builder()
                                    .name("총자산증가율")
                                    .value(variable.totalAssetGrowthRate)
                                    .build(),

                            new AnalysisResultDto().builder()
                                    .name("자기자본증가율")
                                    .value(variable.netWorthGrowthRate)
                                    .build(),

                            new AnalysisResultDto().builder()
                                    .name("순이익증가율")
                                    .value(variable.netProfitGrowthRate)
                                    .build(),

                            new AnalysisResultDto().builder()
                                    .name("주당이익증가율")
                                    .value(variable.earningsPerShareGrowthRate)
                                    .build(),

                            new AnalysisResultDto().builder()
                                    .name("지속가능성장률")
                                    .value(variable.sustainableGrowthRate)
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
