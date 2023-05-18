package com.jobtang.sourcecompany.api.corp.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class CorpInfoDto {
    private String corpId;  // 회사 코드,pk
    private String corpName;  // 회사명
    private String corpImg;  // 로고 이미지
    private List<Info> infoList = new ArrayList<>(); // 기업개요 정보 리스트
}
