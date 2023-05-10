package com.jobtang.sourcecompany.api.corp_detail.util.Analysis;

import com.jobtang.sourcecompany.api.corp_detail.dto.AnalysisDto;
import com.jobtang.sourcecompany.api.corp_detail.dto.AnalysisResultDto;
import com.jobtang.sourcecompany.api.corp_detail.util.variable.AnalysisVariable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public interface AnalysisForm {

    AnalysisDto analysis(AnalysisVariable variable);

    void checkNull();

    List<AnalysisResultDto> calculate();

    AnalysisDto setInfo(List<AnalysisResultDto> data);

}
