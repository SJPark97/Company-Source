package com.jobtang.sourcecompany.api.corp.service;

import com.jobtang.sourcecompany.api.corp.dto.CorpInfoDto;
import com.jobtang.sourcecompany.api.corp.dto.CorpSearchListDto;
import com.jobtang.sourcecompany.api.corp.entity.Corp;

import java.util.HashMap;
import java.util.List;

public interface CorpService {

    List<CorpSearchListDto> searchCorp(String inputValue);

    CorpInfoDto corpInfo(String corpId);

    void makeRandCorp();

//    HashMap<String, Object> randCorp();

    List<CorpSearchListDto> randCorp(int page);

    // 조회수 적용
    void updateViewCorp();
}
