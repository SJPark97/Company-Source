package com.jobtang.sourcecompany.api.corp_detail.util.Analysis.analysis400;

import com.jobtang.sourcecompany.api.corp_detail.dto.AnalysisDto;
import com.jobtang.sourcecompany.api.corp_detail.dto.AnalysisResultDto;
import com.jobtang.sourcecompany.api.corp_detail.util.Analysis.AnalysisForm;
import com.jobtang.sourcecompany.api.corp_detail.util.variable.AnalysisVariable;
import com.jobtang.sourcecompany.api.corp_detail.util.variable.EntityVariable;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@NoArgsConstructor
public class Analysis405 implements AnalysisForm {

    // 변수 설정
    private Boolean isExistAll;

    private AnalysisVariable variable;
    private EntityVariable entity;


    private String analysisId = "405";
    private String analysisName = "Z-Score";

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
                variable.netWorkingCapital == null ||  // 순운전자본
                variable.variable.getTotalAssets() == null ||  // 총자산
                entity.getRetainedEarnings() == null ||  // 이익잉여금
                entity.getNetProfit() == null ||  // 순이익 +
                entity.getTax() == null ||  // 세금
                entity.getInterestExpense() == null || // 이지비용
                entity.getEquityCapital() == null ||  // 자기자본 시장가치
                entity.getSales() == null // 매출액
        ) {
            isExistAll = false;
        }
    }

    @Override
    public List<AnalysisResultDto> calculate() {

        List<AnalysisResultDto> result = new ArrayList<>();
        try {
            AnalysisResultDto analysisResultDto1 = new AnalysisResultDto().builder()
                    .name("Z-Score")
                    .value
                    (
                            0.012 * variable.netWorkingCapital / entity.getTotalAssets() + // (순운전자본 / 총자산)
                            0.014 * entity.getRetainedEarnings() / entity.getTotalAssets() + // (이익잉여금 / 총자산)
                            0.033 * (entity.getNetProfit() + entity.getTax() + entity.getInterestExpense()) / entity.getTotalAssets() +  //(EBIT / 총자산)
                            0.006 * entity.getEquityCapital() / entity.getTotalLiabilities()/ // (자기자본 시장가치 / 총부채)
                            0.0099 * entity.getSales() / entity.getTotalAssets() // (매출액 / 총자산)
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
