package com.jobtang.sourcecompany.api.corp.controller;

import com.jobtang.sourcecompany.api.corp.dto.CorpInfoDto;
import com.jobtang.sourcecompany.api.corp.dto.CorpSearchListDto;
import com.jobtang.sourcecompany.api.corp.entity.Corp;
import com.jobtang.sourcecompany.api.corp.service.CorpService;
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
public class CorpController {
    private final CorpService corpService;

    // 기업 이름 검색
    @GetMapping("/list/{inputValue}")
    public ResponseEntity<List<CorpSearchListDto>> searchCorp(@PathVariable String inputValue) {
        return new ResponseEntity<>(corpService.searchCorp(inputValue), HttpStatus.valueOf(200));
    }

    // 기업 개요 조회
    @GetMapping("/info/{corpId}")
    public ResponseEntity<CorpInfoDto> corpInfo(@PathVariable String corpId) {
        return new ResponseEntity<>(corpService.corpInfo(corpId), HttpStatus.valueOf(200));
    }
}
