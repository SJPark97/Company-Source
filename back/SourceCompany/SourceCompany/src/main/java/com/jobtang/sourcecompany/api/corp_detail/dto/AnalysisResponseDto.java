package com.jobtang.sourcecompany.api.corp_detail.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnalysisResponseDto {
    private String corp_name;
    private String corp_id;
    private String analysis_name;
    private String analysis_method;
    private boolean exist_all;
    private String rate;
    private List<HashMap> result;
    private HashMap analysisInfo;
}
