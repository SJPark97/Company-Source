package com.jobtang.sourcecompany.api.corp_detail.dto.analysis_info;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnalysisInfoDetailDto {
    private String category;
    private List<AnalysisInfoValueDto> value;
}
