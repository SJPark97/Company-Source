package com.jobtang.sourcecompany.api.corp_detail.util.Analysis.analysis400;

import com.jobtang.sourcecompany.api.corp_detail.dto.AnalysisDto;
import com.jobtang.sourcecompany.api.corp_detail.dto.AnalysisResultDto;
import com.jobtang.sourcecompany.api.corp_detail.util.Analysis.AnalysisForm;
import com.jobtang.sourcecompany.api.corp_detail.util.Calculator;
import com.jobtang.sourcecompany.api.corp_detail.util.variable.AnalysisVariable;
import com.jobtang.sourcecompany.api.corp_detail.util.variable.EntityVariable;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@NoArgsConstructor
public class Analysis408 implements AnalysisForm {

    // 변수 설정
    private Boolean isExistAll;

    private AnalysisVariable variable;
    private EntityVariable entity;

    private String analysisId = "408";
    private String analysisName = "한국은행모형";

    @Override
    public AnalysisDto analysis(AnalysisVariable variable) {
        log.info(analysisName+"("+analysisId+")" + "진입");
        this.variable = variable;
        this.isExistAll = true;
        this.entity = variable.variable;

        checkNull();

        return setInfo(calculate());
    }

    @Override
    public void checkNull() {
        if (
                variable.netWorthToTotalAsset == null // 자기자본/총자본
                        || variable.liquidityRatio == null // 유동자산/유동부채
                        || entity.getEquityCapital() == null // (자기자본+비유동부채)/비유동자산 → 비유동장기적합률의 역수
                        || entity.getNonCurrentLiabilities() == null
                        || entity.getNonCurrentAssets() == null
                        || variable.netWorkingCapitalToTotalAsset == null // 순운전자본/총자산
                        || variable.netProfitBeforeTax == null // 세전순이익/자본금
                        || variable.totalAssetTurnover == null // 매출액/총자산
                        || entity.getSales() == null // 매출액/경영자본 = 매출액/총자산 - 투자자산 - 건설중인자산 = 경영자본회전율
                        || variable.turnoverRatioOfTotalOperatingCapital == null // 매출액/경영자본 = 매출액/총자산 - 투자자산 - 건설중인자산 = 경영자본회전율
        ) {
            isExistAll = false;
        }
    }

    @Override
    public List<AnalysisResultDto> calculate() {
        //V = 자기자본/총자본
        //W = 유동자산/유동부채
        //X = (자기자본+비유동부채)/비유동자산 → 비유동장기적합률의 역수
        //Y = 순운전자본/총자산
        //Z = 세전순이익/자본금
        //A = 매출액/총자산
        //B = 매출액/경영자본 = 매출액/총자산 - 투자자산 - 건설중인자산 = 경영자본회전율
        List<AnalysisResultDto> result = new ArrayList<>();


        try {
            Calculator calculator = new Calculator();
            Double x = calculator.myDivision(calculator.myPlus(entity.getEquityCapital(), entity.getNonCurrentLiabilities()), entity.getNonCurrentAssets());
            Double z = calculator.myDivision(variable.netProfitBeforeTax, entity.getEquityCapital());
            Double b = calculator.myDivision(entity.getSales(), variable.turnoverRatioOfTotalOperatingCapital);

            AnalysisResultDto analysisResultDto1 = new AnalysisResultDto().builder()


                    .name("한국은행모형")
                    .value
                    (
                            5.323 -
                            0.033 * variable.netWorthToTotalAsset - // 자기자본/총자본
                            0.026 * variable.liquidityRatio - // 유동자산/유동부채
                            0.008 * x +  // (자기자본+비유동부채)/비유동자산 → 비유동장기적합률의 역수
                            0.060 * variable.netWorkingCapitalToTotalAsset - // 순운전자본/총자산
                            0.004 * z + // 세전순이익/자본금
                            2.271 * variable.totalAssetTurnover - // 매출액/총자산
                            2.783 * b // 매출액/경영자본
                    )
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
