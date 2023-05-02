package com.jobtang.sourcecompany.api.corp_detail.service;

import com.jobtang.sourcecompany.api.corp.entity.Corp;

public interface CorpDetailService {
    void updateCorpDetails(Corp corp);
    void updateCorp();
    void deleteCorp();
    // 테스트용
    void updateOne(String corpId);
}
