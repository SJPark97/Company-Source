package com.jobtang.sourcecompany.api.corp.controller;

import com.jobtang.sourcecompany.api.corp.dto.CorpInfoDto;
import com.jobtang.sourcecompany.api.corp.dto.CorpSearchListDto;
import com.jobtang.sourcecompany.api.corp.entity.Corp;
import com.jobtang.sourcecompany.api.corp.service.CorpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/corp")
@RequiredArgsConstructor
@Api("기업 정보 API")
public class CorpController {
    private final CorpService corpService;

    // 기업 이름 검색
    @ApiOperation(
            value = "사용자 평균 데이터 리스트",
            notes = "사용자 평균 데이터 리스트",
            response = CorpSearchListDto.class,
            responseContainer = "List"
    )
    @GetMapping("/list/{inputValue}")
    public ResponseEntity<List<CorpSearchListDto>> searchCorp(@PathVariable String inputValue) {
        return new ResponseEntity<>(corpService.searchCorp(inputValue), HttpStatus.valueOf(200));
    }

    // 기업 개요 조회
    @ApiOperation(
            value = "기업 개요",
            notes = "기업 개요 반환",
            response = CorpInfoDto.class
    )
    @GetMapping("/info/{corpId}")
    public ResponseEntity<CorpInfoDto> corpInfo(@PathVariable String corpId) {
        return new ResponseEntity<>(corpService.corpInfo(corpId), HttpStatus.valueOf(200));
    }

    // 랜덤기업 레디스에 저장
    @ApiOperation(
            value = "랜덤 기업 생성",
            notes = "레디스에 순서 뒤섞어서 기업 저장",
            response = void.class
    )
    @GetMapping("/makerandomcorp/2kdmqkwm")
    public String makeRandomCorp() {
        corpService.randCorp();
        return "랜덤 기업 저장 완료";
    }
}
