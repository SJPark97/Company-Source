package com.jobtang.sourcecompany.api.corp_detail.controller;

import com.jobtang.sourcecompany.api.corp_detail.service.CorpDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/corp")
@RequiredArgsConstructor
public class CorpDetailController {

    private final CorpDetailService corpDetailService;
    @GetMapping("/update")
    public void updateCorpAnalysis(){
//        corpDetailService.updateCorpDetails();
        log.info("기업분석 업데이트 완료!");
    }
}
