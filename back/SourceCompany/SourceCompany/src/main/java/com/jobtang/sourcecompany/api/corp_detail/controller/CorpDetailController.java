package com.jobtang.sourcecompany.api.corp_detail.controller;

import com.jobtang.sourcecompany.api.corp_detail.service.CorpDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/corpdetail")
@RequiredArgsConstructor
public class CorpDetailController {

    private final CorpDetailService corpDetailService;

    @GetMapping("/update")
    public void updateCorpAnalysis(){
        corpDetailService.updateCorp();
        log.info("기업분석 업데이트 완료!");
    }

    @GetMapping("/update/{corpId}")
    public void updateCorpAnalysis(@PathVariable String corpId){
        corpDetailService.updateOne(corpId);
        log.info("단일 기업 분석 업데이트 완료!");
    }

    @DeleteMapping("/update")
    public void deleteCorpAnalysis(){
        corpDetailService.deleteCorp();
        log.info("기업분석 데이터 삭제 완료!");
    }

}
