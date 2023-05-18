package com.jobtang.sourcecompany.api.corp_detail.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class AnalysisDto {
    private String analysisId;
    private String analysisName;
    private Boolean isExistAll;
    private List<AnalysisResultDto> analysisResult;
}
