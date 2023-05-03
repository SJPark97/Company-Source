package com.jobtang.sourcecompany.api.corp.util;

import com.jobtang.sourcecompany.api.corp.service.CorpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CorpSchedule {

    private final CorpService corpService;

    @Scheduled(cron = "0 0 5 * * ?") // 새벽 5시마다 업데이트
    private void schedule() {
        log.info("스케쥴링 : 기업 조회 업데이트 시작!");
        corpService.updateViewCorp();
        log.info("스케쥴링 : 기업 조회 업데이트 완료!");
    }
}
