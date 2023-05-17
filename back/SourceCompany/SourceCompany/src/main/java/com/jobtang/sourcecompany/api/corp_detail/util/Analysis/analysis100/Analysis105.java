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
public class Analysis105 implements AnalysisForm {

    // 변수 설정
    private Boolean isExistAll;

    private AnalysisVariable variable;

    private String analysisId = "105";
    private String analysisName = "수익성 분석";

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
        if (variable.liquidityRatio == null
                || variable.quickRatio == null
                || variable.inventoryTurnover == null
                || variable.totalAssetTurnover == null
                || variable.operatingProfitToSalesRatio == null
                || variable.returnOnInvestment == null
                || variable.totalAssetsOperatwingProfiRate == null
                || variable.returnOnEquity == null
        ) {
            isExistAll = false;
        }
    }

    @Override
    public List<AnalysisResultDto> calculate() {
//      1. 유동비율 = 유동자산/유동부채 * 100
//      2. 당좌비율 = 당좌자산/유동부채 * 100 = (유동자산-재고자산)/유동부채 * 100
//      3. 재고자산회전율 = 매출액/재고자산(평잔)
//      4. 총자산회전율 = 매출액/총자산(평잔)
//      5. 매출액영업이익률 = 영업이익/매출액 * 100
//      6. 총자본순이익률(ROI)= 순이익/매출액 * 매출액/총자산 = 매출액순이익률 * 총자산회전율 = 매출마진 * 총자산회전속도
//      7. 총자산영업이익률(ROA) = 총자산/영업이익
//      8. 자기자본순이익률(ROE) = ROI * (1+부채비율)
        try {
            return new ArrayList<>(List.of(
                            new AnalysisResultDto().builder()
                                    .name("유동비율")
                                    .value(variable.liquidityRatio)
                                    .build(),

                            new AnalysisResultDto().builder()
                                    .name("당좌비율")
                                    .value(variable.quickRatio)
                                    .build(),

                            new AnalysisResultDto().builder()
                                    .name("재고자산회전율")
                                    .value(variable.inventoryTurnover)
                                    .build(),

                            new AnalysisResultDto().builder()
                                    .name("총자산회전율")
                                    .value(variable.totalAssetTurnover)
                                    .build(),

                            new AnalysisResultDto().builder()
                                    .name("매출액영업이익률")
                                    .value(variable.operatingProfitToSalesRatio)
                                    .build(),

                            new AnalysisResultDto().builder()
                                    .name("총자본순이익률(ROI)")
                                    .value(variable.returnOnInvestment)
                                    .build(),
                            new AnalysisResultDto().builder()
                                    .name("총자산영업이익률(ROA)")
                                    .value(variable.totalAssetsOperatwingProfiRate)
                                    .build(),
                            new AnalysisResultDto().builder()
                                    .name("자기자본순이익률(ROE)")
                                    .value(variable.returnOnEquity)
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
