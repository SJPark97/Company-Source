package com.jobtang.sourcecompany.api.corp.service;

import com.jobtang.sourcecompany.api.corp.dto.CorpInfoDto;
import com.jobtang.sourcecompany.api.corp.dto.CorpSearchListDto;
import com.jobtang.sourcecompany.api.corp.entity.Corp;

import java.util.List;

public interface CorpService {

    List<CorpSearchListDto> searchCorp(String inputValue);

    CorpInfoDto corpInfo(String corpId);

    void randCorp();
}
