package com.jobtang.sourcecompany.api.corp.service;

import com.jobtang.sourcecompany.api.corp.dto.CorpAutoSearchDto;
import com.jobtang.sourcecompany.api.corp.dto.CorpInfoDto;
import com.jobtang.sourcecompany.api.corp.dto.CorpSearchListDto;
import com.jobtang.sourcecompany.api.corp.dto.Info;
import com.jobtang.sourcecompany.api.corp.entity.Corp;
import com.jobtang.sourcecompany.api.corp.repository.CorpRepository;
import com.jobtang.sourcecompany.api.exception.CustomException;
import com.jobtang.sourcecompany.api.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CorpServiceImpl implements CorpService{
    private final CorpRepository corpRepository;
    private final ModelMapper mapper = new ModelMapper();
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

    public List<CorpAutoSearchDto> autoSearchCorp(String inputValue) {
        // value% 형식으로 LIKE 검색
        // totalview 기준으로 역순(내림차순) 정렬
        // 5개만 추출
        return corpRepository.findByCorpNameStartingWith(inputValue).stream()
                .sorted(Comparator.comparing(Corp::getTotalView).reversed())
                .limit(10)
                .map(c -> mapper.map(c, CorpAutoSearchDto.class))
                .collect(Collectors.toList());
    }

//    public List<CorpSearchListDto> recommendCorp() {
//        // 기업 코드를 랜덤 1개 뽑기
//        List<String> indutyCodeList =
//
//        // 해당 기업코드를 가진것 랜덤 5개만 뽑기
//    }

    public CorpInfoDto corpInfo(String corpId) {
        // jpa를 사용해서 해당 corpid로 조회
        HashMap<String,Object> result = new HashMap<>();
        Optional<Corp> corp = corpRepository.findById(corpId);
        // null 에러 처리
        if (corp.isPresent()) {
            // 조회 처리
            corpViewCnt(corp.get());
            System.out.println(corp.toString());
            Corp data = corp.get();
            CorpInfoDto corpInfoDto = mapper.map(data, CorpInfoDto.class);
            List<Info> infoList = new ArrayList<>();
            infoList.add(new Info("기업형태",data.getCorpSize()));
            infoList.add(new Info("산업코드",data.getIndutyCode()));
            infoList.add(new Info("주식코드",data.getStockId()));
            infoList.add(new Info("산업종류",data.getIndutyName()));
            infoList.add(new Info("주소",data.getAddress()));
            infoList.add(new Info("홈페이지",data.getHomepage()));
            infoList.add(new Info("설립일", DateTimeFormatter.ofPattern("yyyy-MM-dd").format(data.getFoundationDate())));
            infoList.add(new Info("사원수",data.getEmployees()+"명"));
            corpInfoDto.setInfoList(infoList);
            return corpInfoDto;
        } else {
            return new CorpInfoDto();
        }
    }

    public void makeRandCorp() {
        // 랜덤 corp 만든거 전부 삭제
//        Set<String> keysSet = redisTemplate.keys("randcorp_*");
//        Iterator<String> keysIterator = keysSet.iterator();
//        while (keysIterator.hasNext()) {
//            redisTemplate.delete(keysIterator.next());
//        }

        // 모든 기업 가져오기
        List<Corp> corps = corpRepository.findAll();
//        List<Corp> corps = corpRepository.findByCorpNameContains("삼성");
        // 무작위로 셔플
        Collections.shuffle(corps);
        // 순회하면서 하나의 기업정보 객체를 CorpSearchListDto로 매핑해주고
        // randcorp_corpId key 형태로 집어넣기
//        for (Corp corp:corps) {
//            ValueOperations<String, CorpSearchListDto> vop = redisTemplate.opsForValue();
//            CorpSearchListDto randCorp = mapper.map(corp, CorpSearchListDto.class);
//            vop.set("randcorp_"+corp.getCorpId(), randCorp);
//        }
        // randcorp:1~N key 형태로 집어넣기
        int idx = 0;
        for (Corp corp:corps) {
            ValueOperations<String, CorpSearchListDto> vop = redisTemplate.opsForValue();
            CorpSearchListDto randCorp = mapper.map(corp, CorpSearchListDto.class);
            vop.set("randcorp:"+idx, randCorp);
            idx += 1;
        }
    }


    @Transactional
    public List<CorpSearchListDto> randCorp(int page) {
        List<CorpSearchListDto> corpSearchListDtoList = new ArrayList<>();
        // 모든 randCorp 기업은 randcorp_corpId 이므로 randcorp_* 형태로 모든 키 가져오기
        // jpa에 비유하면 findAllRandcorp 같은상태
//        Set<String> redisKeys = redisTemplate.keys("randcorp_*");
//        // stream으로
//        List<String> keys = redisKeys.stream()
//                .skip((long) (page-1) *6)
//                .limit(6)
//                .collect(Collectors.toList());
////                .map(key-> corpSearchListDtoList.add(redisTemplate.opsForValue().get(key)))
//        for (String key:keys) {
//            corpSearchListDtoList.add(redisTemplate.opsForValue().get(key));
//        }
        for (int i = (page-1)*6; i < page * 6 ; i++) {
            corpSearchListDtoList.add(redisTemplate.opsForValue().get("randcorp:"+i));
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
        System.out.println("조회수 저장하러 들옴");
        String key = "viewCorp:" + corp.getCorpId();
        ValueOperations<String, Integer> valueOperations = integerRedisTemplate.opsForValue();
        if (valueOperations.get(key) == null) {
            valueOperations.set(key, 1);
        } else {
          Integer viewCnt = valueOperations.get(key);
          valueOperations.set(key, viewCnt +1);
        }
    }

    public void updateViewCorp(){
        List<Corp> corps = corpRepository.findAll();
        for (Corp corp : corps) {
            String key = "viewCorp:" + corp.getCorpId();
            ValueOperations<String, Integer> valueOperations = integerRedisTemplate.opsForValue();
            if (valueOperations.get(key) == null) {
                corp.updateViewCnt(0);
            } else {
                corp.updateViewCnt(valueOperations.get(key));
                redisTemplate.delete(key);
            }
            corpRepository.save(corp);
        }
        log.info("기업분석 조회수 업데이트 완료!");
    }


    @Scheduled(cron = "0 0 3 * * ?") // 새벽 3시마다 업데이트
    public void schedule() {
        log.info("스케쥴링 : 기업 조회 업데이트 시작!");
        updateViewCorp();
        log.info("스케쥴링 : 기업 조회 업데이트 완료!");
    }

    public List<String> getCorpAll() {
        List<String> result = corpRepository.findAllCorpIds();
        return result;
    }

    @Override
    public List<String> getPagingCorpName(int size, int page) {
        Pageable pageSetting = PageRequest.of(size, page);
        Page<Corp> corps = corpRepository.findAllByOrderByCorpId(pageSetting);
        if (corps == null){throw new CustomException(ErrorCode.CORP_NOT_FOUND);}
        List<String> result = new ArrayList<>();
        for (Corp corp : corps) {
            result.add(corp.getCorpName());
        }
        return result;
    }

    @Override
    public List<CorpSearchListDto> getHotCorps(int page, int size) {
        Pageable pageSetting = PageRequest.of(size, page);
        Page<Corp> corps = corpRepository.findAllByOrderByYesterdayViewDesc(pageSetting);
        if (corps == null){
            throw new CustomException(ErrorCode.CORP_NOT_FOUND);
        }
        List<CorpSearchListDto> result = new ArrayList<>();

        for (Corp corp: corps) {
            result.add(mapper.map(corp, CorpSearchListDto.class));
        }

        return result;
    }




}

