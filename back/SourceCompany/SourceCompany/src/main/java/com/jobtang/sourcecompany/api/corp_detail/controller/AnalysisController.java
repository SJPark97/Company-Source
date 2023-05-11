package com.jobtang.sourcecompany.api.corp_detail.controller;

import com.jobtang.sourcecompany.api.corp_detail.service.AnalysisService;
import com.jobtang.sourcecompany.util.ResponseHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/analysis")
@RequiredArgsConstructor
public class AnalysisController {

    private final AnalysisService analysisService;
    private final ResponseHandler responseHandler;

    @GetMapping("/{analysisId}/{corpId}")
    public ResponseEntity getAnalysis(@PathVariable String analysisId, @PathVariable String corpId) {
        return responseHandler.response(analysisService.getCorpAnalysis(analysisId, corpId, 0));
    }

    @GetMapping("/update/analysisInfo")
    public void updateAnalysisInfo(){
        analysisService.updateAnalysisInfo();
        log.info("기업분석 업데이트 완료!");
    }

    @GetMapping("/update/all")
    public ResponseEntity updateAnalysisAllCorp(){
        analysisService.updateAnalysisAllCorp();
        return new ResponseEntity("완료",HttpStatus.OK);
    }

    @GetMapping("/update/etc/gpt/{corpId}}")
    public ResponseEntity updateAnalysisGpt(@PathVariable String corpId){
        analysisService.updateAnalysisAllCorp();
        return new ResponseEntity("완료",HttpStatus.OK);
    }

    @GetMapping("/update/{corpId}")
    public ResponseEntity updateAnalysisCorp(@PathVariable String corpId){
        analysisService.updateAnalysisGpt(corpId);
        return new ResponseEntity("완료",HttpStatus.OK);
    }
}
