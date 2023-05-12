package com.jobtang.sourcecompany.api.corp_detail.dto.comparison;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComparisonResultDto {
    private String analysis_name;
    private String analysis_method;
    private boolean exist_all;
    private List<HashMap> result;
    private HashMap analysisInfo;
}
