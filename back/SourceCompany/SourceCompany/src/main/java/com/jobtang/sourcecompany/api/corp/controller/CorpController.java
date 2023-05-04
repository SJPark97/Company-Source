package com.jobtang.sourcecompany.api.corp.controller;

import com.jobtang.sourcecompany.api.corp.dto.CorpInfoDto;
import com.jobtang.sourcecompany.api.corp.dto.CorpSearchListDto;
import com.jobtang.sourcecompany.api.corp.entity.Corp;
import com.jobtang.sourcecompany.api.corp.service.CorpService;
import io.lettuce.core.dynamic.annotation.Param;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
            value = "기업 검색",
            notes = "검색명으로 LIKE 쿼리로 모든 기업명 반환",
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
    public ResponseEntity<?> corpInfo(@PathVariable String corpId) {
        HashMap<String,Object> result = new HashMap<>();
        CorpInfoDto corpInfoDto = corpService.corpInfo(corpId);
        if (corpInfoDto == null) {
            result.put("status", "400");
            result.put("message", "잘못된 요청입니다");
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        result.put("data", corpInfoDto);
        result.put("message", "");
        result.put("status", 200);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 랜덤기업 레디스에 저장
    @ApiOperation(
            value = "랜덤 기업 생성",
            notes = "레디스에 순서 뒤섞어서 기업 저장",
            response = void.class
    )
    @GetMapping("/makerandomcorp/2kdmqkwm")
    public String makeRandomCorp() {
        corpService.makeRandCorp();
        return "랜덤 기업 저장 완료";
    }

    // 홈화면 랜덤 기업 리스트 출력
    @ApiOperation(
            value = "랜덤 기업 출력",
            notes = "홈 화면에 랜덤으로 섞은 기업 출력",
            response = CorpSearchListDto.class,
            responseContainer = "List"
    )
    @GetMapping("/randcorp/{page}")
    public ResponseEntity<?> randCorp(@PathVariable int page) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("totalPage", "385");
        return new ResponseEntity<>(corpService.randCorp(page), headers, HttpStatus.valueOf(200));
    }

    @GetMapping("/all")
    public ResponseEntity getCorpAll() {
        List<String> result = corpService.getCorpAll();
        return new ResponseEntity<List<String>>(result, HttpStatus.OK);
    }

    @GetMapping("/hotcorp")
    public ResponseEntity<?> getHotCorp(int page, int size) {
        List<CorpSearchListDto> data = corpService.getHotCorps(size, page);
        HashMap<String, Object> result = new HashMap<>();
        if (data == null) {
            result.put("status", "400");
            result.put("message", "잘못된 요청입니다");
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        result.put("data", data);
        result.put("message", "");
        result.put("status", 200);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
