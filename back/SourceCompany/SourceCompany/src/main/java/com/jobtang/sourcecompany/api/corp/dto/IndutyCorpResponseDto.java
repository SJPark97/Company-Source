package com.jobtang.sourcecompany.api.corp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IndutyCorpResponseDto {
    private String IndutyName;
    private List<CorpSearchListDto> corps;
}
