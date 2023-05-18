package com.jobtang.sourcecompany.api.corp_detail.util.Analysis.analysis100;

import com.jobtang.sourcecompany.api.corp.repository.CorpRepository;
import com.jobtang.sourcecompany.api.corp_detail.dto.AnalysisDto;
import com.jobtang.sourcecompany.api.corp_detail.dto.AnalysisResultDto;
import com.jobtang.sourcecompany.api.corp_detail.util.Analysis.AnalysisForm;
import com.jobtang.sourcecompany.api.corp_detail.util.Calculator;
import com.jobtang.sourcecompany.api.corp_detail.util.variable.AnalysisVariable;
import com.jobtang.sourcecompany.api.induty_detail.repository.IndutyDetailRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@NoArgsConstructor
public class Analysis114 implements AnalysisForm {
    // 업데이트 필요
    // 변수 설정
    private Boolean isExistAll;

    private AnalysisVariable variable;
    private AnalysisVariable indutyVariable;

    @Autowired
    private Calculator calculator;

    private String analysisId = "114";
    private String analysisName = "지수법 분석";

    @Autowired
    private IndutyDetailRepository indutyDetailRepository;

    @Autowired
    private CorpRepository corpRepository;

    @Override
    public AnalysisDto analysis(AnalysisVariable variable) {
        log.info(analysisName+"("+analysisId+")" + "진입");

        this.variable = variable;

        this.isExistAll = true;

        if (variable.isInduty == true) {
            return new AnalysisDto().builder()
                    .analysisId(analysisId)
                    .analysisName(analysisName)
                    .isExistAll(false)
                    .analysisResult(null)
                    .build();
        }
        checkNull();

        return setInfo(calculate());
    }

    @Override
    public void checkNull() {
        try {
        if (variable.liquidityRatio == null
                || variable.debtToEquityRatio == null
                || variable.nonCurrentRatio == null
                || variable.receivablesTurnover == null
                || variable.inventoryTurnover == null
                || variable.payablesTurnover == null
                || variable.variable.getCurrentAsset() == null
                || variable.variable.getTradeReceivables() == null
                || variable.variable.getInventories() == null
                || variable.totalAssetTurnover == null
                || variable.timesInterestEarned == null
                || variable.earningsAfterTaxToSalesRatio == null
                || variable.returnOnAssets == null

                || indutyVariable.liquidityRatio == null
                || indutyVariable.debtToEquityRatio == null
                || indutyVariable.nonCurrentRatio == null
                || indutyVariable.receivablesTurnover == null
                || indutyVariable.inventoryTurnover == null
                || indutyVariable.payablesTurnover == null
                || indutyVariable.variable.getCurrentAsset() == null
                || indutyVariable.variable.getTradeReceivables() == null
                || indutyVariable.variable.getInventories() == null
                || indutyVariable.totalAssetTurnover == null
                || indutyVariable.timesInterestEarned == null
                || indutyVariable.earningsAfterTaxToSalesRatio == null
                || indutyVariable.returnOnAssets == null
        ) {
            isExistAll = false;
        }}catch (Exception e) {
            isExistAll = false;
        }
    }

    @Override
    public List<AnalysisResultDto> calculate() {
        try {
            return new ArrayList<>(List.of(
                            new AnalysisResultDto().builder()
                                    .name("월의 지수법")
                                    .value(
                    0.25 * calculator.myRatio(variable.liquidityRatio, indutyVariable.liquidityRatio) +
                    0.25 * calculator.myRatio(variable.debtToEquityRatio, indutyVariable.debtToEquityRatio) +
                    0.25 * calculator.myRatio(variable.nonCurrentRatio, indutyVariable.nonCurrentRatio) +
                    0.25 * calculator.myRatio(variable.receivablesTurnover, indutyVariable.receivablesTurnover) +
                    0.25 * calculator.myRatio(variable.inventoryTurnover, indutyVariable.inventoryTurnover) +
                    0.25 * calculator.myRatio(variable.nonCurrentAssetTurnover, indutyVariable.nonCurrentAssetTurnover) +
                    0.25 * calculator.myRatio(variable.equityTurnover, indutyVariable.equityTurnover)
                                    ).build(),

                            new AnalysisResultDto().builder()
                                    .name("트랜드의 지수법")
                                    .value(
                    0.15 * calculator.myRatio(variable.liquidityRatio, indutyVariable.liquidityRatio) +
                    0.10 * calculator.myRatio(variable.debtToEquityRatio, indutyVariable.debtToEquityRatio) +
                    0.10 * calculator.myRatio(variable.nonCurrentRatio, indutyVariable.nonCurrentRatio) +
                    0.10 * calculator.myRatio(variable.receivablesTurnover, indutyVariable.receivablesTurnover) +
                    0.20 * calculator.myRatio(variable.inventoryTurnover, indutyVariable.inventoryTurnover) +
                    0.20 * calculator.myRatio(variable.nonCurrentAssetTurnover, indutyVariable.nonCurrentAssetTurnover) +
                    0.15 * calculator.myRatio(variable.payablesTurnover, indutyVariable.payablesTurnover)
                                    ).build(),

                            new AnalysisResultDto().builder()
                                    .name("브리체트의 지수법")
                                    .value(
                    0.20 * calculator.myRatio(variable.liquidityRatio, indutyVariable.liquidityRatio) +
                    0.20 * calculator.myRatio(calculator.myDivision(variable.quickAsset, variable.variable.getCurrentAsset()), calculator.myDivision(indutyVariable.quickAsset, indutyVariable.variable.getCurrentAsset())) +
                    0.05 * calculator.myRatio(calculator.myDivision(variable.variable.getTradeReceivables(), variable.variable.getInventories()), calculator.myDivision(indutyVariable.variable.getTradeReceivables(), indutyVariable.variable.getInventories())) +
                    0.05 * calculator.myRatio(variable.debtToEquityRatio, indutyVariable.debtToEquityRatio) +
                    0.10 * calculator.myRatio(variable.inventoryTurnover, indutyVariable.inventoryTurnover) +
                    0.25 * calculator.myRatio(variable.receivablesTurnover, indutyVariable.receivablesTurnover) +
                    0.05 * calculator.myRatio(variable.totalAssetTurnover, indutyVariable.totalAssetTurnover) +
                    0.02 * calculator.myRatio(variable.timesInterestEarned, indutyVariable.timesInterestEarned) +
                    0.05 * calculator.myRatio(variable.earningsAfterTaxToSalesRatio, indutyVariable.earningsAfterTaxToSalesRatio) +
                    0.03 * calculator.myRatio(variable.returnOnAssets, indutyVariable.returnOnAssets)
                                    ).build()
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
