package com.jobtang.sourcecompany.api.analysis_result.Dto;

import com.jobtang.sourcecompany.api.corp.dto.CorpSearchListDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoodCorpResponseDto {
    private String analysisName;
    private List<CorpSearchListDto> corps;
}
