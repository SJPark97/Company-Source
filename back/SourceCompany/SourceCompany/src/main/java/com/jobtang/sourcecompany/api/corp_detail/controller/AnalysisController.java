package com.jobtang.sourcecompany.api.corp_detail.controller;

import com.jobtang.sourcecompany.api.corp_detail.service.AnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("api/v1/analysis")
@RequiredArgsConstructor
public class AnalysisController {

    private final AnalysisService analysisService;

    @GetMapping("/{analysisId}/{corpId}")
    public ResponseEntity getAnalysis(@PathVariable int analysisId, @PathVariable String corpId) {
        HashMap result = new HashMap();

        HashMap data = analysisService.getCorpAnalysis(analysisId, corpId);

        if (data == null) {
            result.put("status", "400");
            result.put("message", "잘못된 요청입니다");
            return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
        }

        result.put("data", data);
        result.put("message", "");
        result.put("status", 200);
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
