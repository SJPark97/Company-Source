package com.jobtang.sourcecompany.api.corp.dto;

import com.jobtang.sourcecompany.api.corp_detail.entity.CorpDetail;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class CorpInfoDto {
    private String corpId;  // 회사 코드,pk
    private String corpName;  // 회사명
    private String corpImg;  // 로고 이미지
//    private String corpSize; // 회사 규모 ( 대기업 , 중소기업 , 중견기업 ...)
//    private String indutyCode; // 산업코드
//    private String indutyName; // 산업명
//    private String homepage; // 홈페이지주소
//    private String employees; // 사원수
//    private LocalDateTime foundationDate; // 설립일
//    private String address; // 회사주소
    private List<Info> infoList = new ArrayList<>(); // 기업개요 정보 리스트
}
