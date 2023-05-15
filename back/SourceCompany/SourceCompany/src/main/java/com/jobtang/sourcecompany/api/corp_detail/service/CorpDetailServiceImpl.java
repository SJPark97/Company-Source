package com.jobtang.sourcecompany.api.corp_detail.service;

import com.jobtang.sourcecompany.api.corp.dto.CorpListResponseDto;
import com.jobtang.sourcecompany.api.corp.dto.CorpSearchListDto;
import com.jobtang.sourcecompany.api.corp_detail.entity.CorpDetail;
import com.jobtang.sourcecompany.api.corp_detail.repository.CorpDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CorpDetailServiceImpl implements CorpDetailService{
    private final CorpDetailRepository corpDetailRepository;
    private final ModelMapper mapper;

    @Override
    public CorpListResponseDto getTopSalesCorps(int size, int page) {
        Pageable pageSetting = PageRequest.of(size, page);
        Page<CorpDetail> corps = corpDetailRepository.findALlByOrderBySalesDesc(pageSetting);

        List<CorpSearchListDto> data = new ArrayList<>();
        for (CorpDetail corpDetail : corps) {
            data.add(mapper.map(corpDetail.getCorp(), CorpSearchListDto.class));
        }
        return new CorpListResponseDto().builder()
                .kind("매출액")
                .corps(data)
                .build();
    }
}
