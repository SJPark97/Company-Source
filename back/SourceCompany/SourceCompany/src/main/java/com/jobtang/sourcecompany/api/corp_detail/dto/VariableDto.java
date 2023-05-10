package com.jobtang.sourcecompany.api.corp_detail.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VariableDto {
    private String variableId;
    private String variableName;
    private List<AnalysisDto> data;
}
