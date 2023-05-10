package com.jobtang.sourcecompany.api.corp_detail.dto;

import lombok.*;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class AnalysisResultDto {
    private String name;
    private Double value;

    public void round() {
        this.value = Math.round(value * 100.0) / 100.0;
    }
}
