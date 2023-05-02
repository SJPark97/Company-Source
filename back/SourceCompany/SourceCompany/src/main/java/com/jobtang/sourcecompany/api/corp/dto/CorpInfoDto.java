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
    private int yesterdayView; // 어제 조회수
    private int totalView; // 총 조회수
    private List<Info> infoList = new ArrayList<>(); // 기업개요 정보 리스트
}
