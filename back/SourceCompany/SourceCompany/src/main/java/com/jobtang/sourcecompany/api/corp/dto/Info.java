package com.jobtang.sourcecompany.api.corp.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Info {
    private String title;
    private Object content;

    public Info (String title, Object content) {
        this.title = title;
        this.content = content;
    }

//    private String corpSize; // 기업형태 ( 대기업 , 중소기업 , 중견기업 ...)
//    private String indutyCode; // 산업코드
//    private String stockId;  // 주식코드
//    private String indutyName; // 산업종류
//    private String address; // 주소
//    private String homepage; // 홈페이지
//    private LocalDateTime foundationDate; // 설립일
//    private int employees; // 사원수
}
