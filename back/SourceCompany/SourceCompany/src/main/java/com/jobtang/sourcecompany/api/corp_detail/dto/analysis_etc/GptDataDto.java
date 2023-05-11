package com.jobtang.sourcecompany.api.corp_detail.dto.analysis_etc;

import lombok.Data;

@Data
public class GptDataDto {
    private int usedTokenNum;
    private String content;

    public GptDataDto(int usedTokenNum, String content) {
        this.usedTokenNum = usedTokenNum;
        this.content = content;
    }
}
