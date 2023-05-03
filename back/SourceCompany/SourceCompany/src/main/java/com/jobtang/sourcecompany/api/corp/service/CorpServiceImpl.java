package com.jobtang.sourcecompany.api.corp.service;

import com.jobtang.sourcecompany.api.corp.dto.CorpInfoDto;
import com.jobtang.sourcecompany.api.corp.dto.CorpSearchListDto;
import com.jobtang.sourcecompany.api.corp.entity.Corp;
import com.jobtang.sourcecompany.api.corp.repository.CorpRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CorpServiceImpl implements CorpService{
    private final CorpRepository corpRepository;
    private final ModelMapper mapper = new ModelMapper();
    @Autowired
    private final RedisTemplate<String, CorpSearchListDto> redisTemplate;
    private final RedisTemplate<String, Integer> integerRedisTemplate;

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
        // jpa를 사용해서 해당 corpid로 조회
        Optional<Corp> corp = corpRepository.findById(corpId);
        // null 에러 처리
        if (corp.isPresent()) {
            //기업분석조회 처리
            corpViewCnt(corp.get());
            return mapper.map(corp.get(), CorpInfoDto.class);
        } else {
            return new CorpInfoDto();
        }
    }

    public void makeRandCorp() {
        // 모든 기업 가져오기
        List<Corp> corps = corpRepository.findAll();
//        List<Corp> corps = corpRepository.findByCorpNameContains("삼성");
        // 무작위로 셔플
        Collections.shuffle(corps);
        // 순회하면서 하나의 기업정보 객체를 CorpSearchListDto로 매핑해주고
        // randcorp_corpId key 형태로 집어넣기
        for (Corp corp:corps) {
            ValueOperations<String, CorpSearchListDto> vop = redisTemplate.opsForValue();
            CorpSearchListDto randCorp = mapper.map(corp, CorpSearchListDto.class);
            vop.set("randcorp_"+corp.getCorpId(), randCorp);
        }
    }


    @Transactional
    public List<CorpSearchListDto> randCorp(int page) {
        List<CorpSearchListDto> corpSearchListDtoList = new ArrayList<>();
        // 모든 randCorp 기업은 randcorp_corpId 이므로 randcorp_* 형태로 모든 키 가져오기
        Set<String> redisKeys = redisTemplate.keys("randcorp_*");
        // stream으로
        List<String> keys = redisKeys.stream()
                .skip((long) (page-1) *6)
                .limit(6)
                .collect(Collectors.toList());
//                .map(key-> corpSearchListDtoList.add(redisTemplate.opsForValue().get(key)))
        for (String key:keys) {
            corpSearchListDtoList.add(redisTemplate.opsForValue().get(key));
        }

        // 순회를 위해(hasNext) Iterator로 변경
//        Iterator<String> it = redisKeys.iterator();
        // 순회
//        while (it.hasNext()) {
//            String data = it.next();
//            // DtoList에 redisTemplate 값을 가져와서 넣기.
//            // 이미 redisTemplate에 value를 CorpSearchListDto로 지정
//            corpSearchListDtoList.add(redisTemplate.opsForValue().get(data));
//        }
        return corpSearchListDtoList;
    }

    // 기업 조회시 Redis에서 조회수 증가
    private void corpViewCnt(Corp corp){
        corp.getCorpId();
        ValueOperations<String, Integer> valueOperations = integerRedisTemplate.opsForValue();
        if (valueOperations == null) {
            valueOperations.set("viewCorp"+corp.getCorpId(), 1);
            System.out.println();
        }



    }
}

// viewCorp77777777
