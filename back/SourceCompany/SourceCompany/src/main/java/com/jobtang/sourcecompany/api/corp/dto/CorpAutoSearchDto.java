package com.jobtang.sourcecompany.api.corp.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CorpAutoSearchDto {
    private String corpId;  // 회사 코드
    private String corpName;  // 회사명
}
