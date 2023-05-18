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
public class Analysis106 implements AnalysisForm {

    // 변수 설정
    private Boolean isExistAll;

    private AnalysisVariable variable;

    private String analysisId = "106";
    private String analysisName = "활동성 분석";

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
        if (variable.totalAssetTurnover == null
                || variable.equityTurnover == null
                || variable.nonCurrentAssetTurnover == null
                || variable.inventoryTurnover == null
                || variable.receivablesTurnover == null
                || variable.payablesTurnover == null
        ) {
            isExistAll = false;
        }
    }

    @Override
    public List<AnalysisResultDto> calculate() {
//      1. 총자산회전율 = 매출액/총자산(평잔)
//      2. 자기자본회전율 = 매출액/자기자본(평잔)
//      3. 비유동자산회전율 = 매출액/비유동자산(평잔)
//      4. 재고자산회전율 = 매출액/재고자산(평잔)
//      5. 매출채권회전율 = 매출액/매출채권(평잔)
//      6. 매입채무회전율 = 매출액/매입채무(평잔)
        try {
            return new ArrayList<>(List.of(
                            new AnalysisResultDto().builder()
                                    .name("총자산회전율")
                                    .value(variable.totalAssetTurnover)
                                    .build(),

                            new AnalysisResultDto().builder()
                                    .name("자기자본회전율")
                                    .value(variable.equityTurnover)
                                    .build(),

                            new AnalysisResultDto().builder()
                                    .name("비유동자산회전율")
                                    .value(variable.nonCurrentAssetTurnover)
                                    .build(),

                            new AnalysisResultDto().builder()
                                    .name("재고자산회전율")
                                    .value(variable.inventoryTurnover)
                                    .build(),

                            new AnalysisResultDto().builder()
                                    .name("매출채권회전율")
                                    .value(variable.receivablesTurnover)
                                    .build(),

                            new AnalysisResultDto().builder()
                                    .name("매입채무회전율")
                                    .value(variable.payablesTurnover)
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
