package com.jobtang.sourcecompany.api.corp_detail.util.Analysis.analysis100;

import com.jobtang.sourcecompany.api.corp_detail.dto.AnalysisDto;
import com.jobtang.sourcecompany.api.corp_detail.dto.AnalysisResultDto;
import com.jobtang.sourcecompany.api.corp_detail.util.Analysis.AnalysisForm;
import com.jobtang.sourcecompany.api.corp_detail.util.variable.AnalysisVariable;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@NoArgsConstructor
public class Analysis101 implements AnalysisForm {

    // 변수 설정
    private Boolean isExistAll;

    private AnalysisVariable variable;

    private String analysisId = "101";
    private String analysisName = "유동성 분석";

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
        if (variable.liquidityRatio == null || variable.quickRatio == null || variable.cashRatio == null || variable.netWorkingCapitalToTotalAsset == null) {
            isExistAll = false;
        }
    }

    @Override
    public List<AnalysisResultDto> calculate() {
//        1. 유동비율 = 유동자산/유동부채 * 100
//        2. 당좌비율 = 당좌자산/유동부채 * 100 = (유동자산-재고자산)/유동부채 * 100
//        3. 현금비율 = 현금및현금성자산/유동부채 * 100
//        4. 순운전자본비율 = 순운전자본/총자산 * 100 = (유동자산-유동부채)/총자산 * 100

        List<AnalysisResultDto> result = new ArrayList<>();
        try {
            AnalysisResultDto analysisResultDto1 = new AnalysisResultDto().builder()
                    .name("유동비율")
                    .value(variable.liquidityRatio)
                    .build();
            analysisResultDto1.round();
            result.add(analysisResultDto1);

            AnalysisResultDto analysisResultDto2 = new AnalysisResultDto().builder()
                    .name("당좌비율")
                    .value(variable.quickRatio)
                    .build();
            analysisResultDto2.round();
            result.add(analysisResultDto2);

            AnalysisResultDto analysisResultDto3 = new AnalysisResultDto().builder()
                    .name("현금비율")
                    .value(variable.cashRatio)
                    .build();
            analysisResultDto3.round();
            result.add(analysisResultDto3);

            AnalysisResultDto analysisResultDto4 = new AnalysisResultDto().builder()
                    .name("순운전자본비율")
                    .value(variable.netWorkingCapitalToTotalAsset)
                    .build();
            analysisResultDto4.round();
            result.add(analysisResultDto4);

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
