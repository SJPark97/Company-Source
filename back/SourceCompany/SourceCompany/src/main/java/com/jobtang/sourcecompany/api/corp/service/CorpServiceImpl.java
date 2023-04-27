package com.jobtang.sourcecompany.api.corp.service;

import com.jobtang.sourcecompany.api.corp.dto.CorpInfoDto;
import com.jobtang.sourcecompany.api.corp.dto.CorpSearchListDto;
import com.jobtang.sourcecompany.api.corp.entity.Corp;
import com.jobtang.sourcecompany.api.corp.repository.CorpRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CorpServiceImpl implements CorpService{
    private final CorpRepository corpRepository;
    private final ModelMapper mapper = new ModelMapper();
    @Autowired
    private final RedisTemplate<String, CorpSearchListDto> redisTemplate;

    public List<CorpSearchListDto> searchCorp(String inputValue) {
        // %value% 형식으로 LIKE 검색
        // totalview 기준으로 역순(내림차순) 정렬
        // Dto 형식대로 매핑
        return corpRepository.findByCorpNameContains(inputValue).stream()
                .sorted(Comparator.comparing(Corp::getTotalView).reversed())
                .map(c -> mapper.map(c, CorpSearchListDto.class))
                .collect(Collectors.toList());
    }

    public CorpInfoDto corpInfo(String corpId) {
        Optional<Corp> corp = corpRepository.findById(corpId);
        if (corp.isPresent()) {
            return mapper.map(corp.get(), CorpInfoDto.class);
        } else {
            return new CorpInfoDto();
        }
    }

    public void randCorp() {
        List<Corp> corps = corpRepository.findAll();
//        List<Corp> corps = corpRepository.findByCorpNameContains("삼성");
        Collections.shuffle(corps);
        for (Corp corp:corps) {
            ValueOperations<String, CorpSearchListDto> vop = redisTemplate.opsForValue();
            CorpSearchListDto randCorp = mapper.map(corp, CorpSearchListDto.class);
            vop.set("randcorp_"+corp.getCorpId(), randCorp);
        }
    }
}
