package com.jobtang.sourcecompany.api.corp_detail.dto.analysis_info;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnalysisInfoDto {
    private String analysis_id;
    private String analysis_name;
    private String analysis_source;
    private String analysis_description;
    private List<AnalysisInfoDetailDto> analysis_detail;
}
