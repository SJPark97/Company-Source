package com.jobtang.sourcecompany.api.corp_detail.controller;

import com.jobtang.sourcecompany.api.corp_detail.service.AnalysisService;
import com.jobtang.sourcecompany.util.ResponseHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/analysis")
@RequiredArgsConstructor
@Api("기업분석")
public class AnalysisController {

    private final AnalysisService analysisService;
    private final ResponseHandler responseHandler;

    @ApiOperation(
            value = "기업 분석 결과 조회",
            notes = "분석코드와 기업코드로 기업 분석 결과 조회")
    @GetMapping("/{analysisId}/{corpId}")
    public ResponseEntity getAnalysis(@PathVariable String analysisId, @PathVariable String corpId) {
        return responseHandler.response(analysisService.getCorpAnalysis(analysisId, corpId, 0));
    }

    @ApiOperation(
            value = "기업 비교 결과 조회",
            notes = "기업간 분석 결과 조회")
    @GetMapping("/comparison/{corpIdA}/{corpIdB}")
    public ResponseEntity getComparison(@PathVariable String corpIdA, @PathVariable String corpIdB) {
        return responseHandler.response(analysisService.getCorpComparison(corpIdA, corpIdB));
    }

    @ApiOperation(
            value = "ChatGpt 분석 결과 조회",
            notes = "ChatGpt 분석 결과 조회")
    @GetMapping("/gpt/{corpId}")
    public ResponseEntity getAnalysisGpt(@PathVariable String corpId) {
        return responseHandler.response(analysisService.getGptAnalysis(corpId));
    }

    // 업데이트

//    @ApiOperation(
//            value = "분석법 정보 업데이트",
//            notes = "MongoDB의 분석법 업데이트")
//    @GetMapping("/update/analysisInfo")
//    public ResponseEntity updateAnalysisInfo(){
//        analysisService.updateAnalysisInfo();
//        return responseHandler.response("완료");
//    }

    @ApiOperation(
            value = "모든 기업 분석 업데이트",
            notes = "MongoDB의 모든 기업 분석 업데이트 및 모든 corpDetail의 기업 분석 결과 업데이트")
    @GetMapping("/update/all")
    public ResponseEntity updateAnalysisAllCorp(){
        analysisService.updateAnalysisAllCorp();
        return responseHandler.response("완료");
    }

    @ApiOperation(
            value = "ChatGPT 분석 업데이트",
            notes = "MongoDB의 ChatGPT 기업 분석 업데이트")
    @GetMapping("/update/etc/gpt")
    public ResponseEntity updateAnalysisGptAll(int size){
        analysisService.updateAnalysisGptAll(size);
        return responseHandler.response("완료");
    }

}
