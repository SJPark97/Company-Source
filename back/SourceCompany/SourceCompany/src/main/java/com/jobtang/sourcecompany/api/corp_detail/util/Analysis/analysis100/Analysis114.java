package com.jobtang.sourcecompany.api.corp_detail.util.Analysis.analysis100;

import com.jobtang.sourcecompany.api.corp.repository.CorpRepository;
import com.jobtang.sourcecompany.api.corp_detail.dto.AnalysisDto;
import com.jobtang.sourcecompany.api.corp_detail.dto.AnalysisResultDto;
import com.jobtang.sourcecompany.api.corp_detail.util.Analysis.AnalysisForm;
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
        try {
            this.indutyVariable = new AnalysisVariable(indutyDetailRepository.findByIndutyCode(corpRepository.findByCorpId(variable.variableId).getIndutyCode()));
        } catch (Exception e) {
            this.isExistAll = false;
            return setInfo(calculate());
        }

        this.isExistAll = true;
        checkNull();

        return setInfo(calculate());
    }

    @Override
    public void checkNull() {
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
        }
    }

    @Override
    public List<AnalysisResultDto> calculate() {
        try {
            return new ArrayList<>(List.of(
                            new AnalysisResultDto().builder()
                                    .name("월의 지수법")
                                    .value(
                    0.25 * variable.liquidityRatio / indutyVariable.liquidityRatio * 100 +
                    0.25 * variable.debtToEquityRatio / indutyVariable.debtToEquityRatio * 100 +
                    0.15 * variable.nonCurrentRatio / indutyVariable.nonCurrentRatio * 100 +
                    0.10 * variable.receivablesTurnover / indutyVariable.receivablesTurnover * 100 +
                    0.10 * variable.inventoryTurnover / indutyVariable.inventoryTurnover * 100 +
                    0.10 * variable.nonCurrentAssetTurnover / indutyVariable.nonCurrentAssetTurnover * 100 +
                    0.05 * variable.equityTurnover / indutyVariable.equityTurnover * 100
                                    ).build(),

                            new AnalysisResultDto().builder()
                                    .name("트랜드의 지수법")
                                    .value(
                    0.15 * variable.liquidityRatio / indutyVariable.liquidityRatio * 100 +
                    0.10 * variable.debtToEquityRatio / indutyVariable.debtToEquityRatio * 100 +
                    0.10 * variable.nonCurrentRatio / indutyVariable.nonCurrentRatio * 100 +
                    0.10 * variable.receivablesTurnover / indutyVariable.receivablesTurnover * 100 +
                    0.20 * variable.inventoryTurnover / indutyVariable.inventoryTurnover * 100 +
                    0.20 * variable.nonCurrentAssetTurnover / indutyVariable.nonCurrentAssetTurnover * 100 +
                    0.15 * variable.payablesTurnover / indutyVariable.payablesTurnover * 100
                                    ).build(),

                            new AnalysisResultDto().builder()
                                    .name("브리체트의 지수법")
                                    .value(
                    0.20 * variable.liquidityRatio / indutyVariable.liquidityRatio * 100 +
                    0.20 * (variable.quickAsset/variable.variable.getCurrentAsset()) / (indutyVariable.quickAsset/indutyVariable.variable.getCurrentAsset()) * 100 +
                    0.05 * (variable.variable.getTradeReceivables()/variable.variable.getInventories()) / (indutyVariable.variable.getTradeReceivables()/indutyVariable.variable.getInventories()) * 100 +
                    0.05 * variable.debtToEquityRatio / indutyVariable.debtToEquityRatio * 100 +
                    0.10 * variable.inventoryTurnover / indutyVariable.inventoryTurnover * 100 +
                    0.25 * variable.receivablesTurnover / indutyVariable.receivablesTurnover * 100 +
                    0.05 * variable.totalAssetTurnover / indutyVariable.totalAssetTurnover * 100 +
                    0.02 * variable.timesInterestEarned / indutyVariable.timesInterestEarned * 100 +
                    0.05 * variable.earningsAfterTaxToSalesRatio / indutyVariable.earningsAfterTaxToSalesRatio * 100 +
                    0.03 * variable.returnOnAssets / indutyVariable.returnOnAssets * 100
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
