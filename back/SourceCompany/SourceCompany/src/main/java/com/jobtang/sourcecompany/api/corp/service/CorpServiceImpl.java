package com.jobtang.sourcecompany.api.corp.service;

import com.jobtang.sourcecompany.api.corp.dto.CorpInfoDto;
import com.jobtang.sourcecompany.api.corp.dto.CorpSearchListDto;
import com.jobtang.sourcecompany.api.corp.entity.Corp;
import com.jobtang.sourcecompany.api.corp.repository.CorpRepo;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CorpServiceImpl implements CorpService{
    private final CorpRepo corpRepo;
    private final ModelMapper mapper = new ModelMapper();

    public List<CorpSearchListDto> searchCorp(String inputValue) {
        // %value% 형식으로 LIKE 검색
        // totalview 기준으로 역순(내림차순) 정렬
        // Dto 형식대로 매핑
        return corpRepo.findByCorpNameContains(inputValue).stream()
                .sorted(Comparator.comparing(Corp::getTotalView).reversed())
                .map(c -> mapper.map(c, CorpSearchListDto.class))
                .collect(Collectors.toList());
    }

    public CorpInfoDto corpInfo(String corpId) {
        Optional<Corp> corp = corpRepo.findById(corpId);
        if (corp.isPresent()) {
            return mapper.map(corp.get(), CorpInfoDto.class);
        } else {
            return new CorpInfoDto();
        }
    }
}
