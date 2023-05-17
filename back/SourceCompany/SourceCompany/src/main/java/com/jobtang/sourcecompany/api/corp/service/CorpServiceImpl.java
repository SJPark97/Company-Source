package com.jobtang.sourcecompany.api.corp.service;

import com.jobtang.sourcecompany.api.analysis_result.service.AnalysisResultService;
import com.jobtang.sourcecompany.api.corp.dto.*;
import com.jobtang.sourcecompany.api.corp.entity.Corp;
import com.jobtang.sourcecompany.api.corp.repository.CorpRepository;
import com.jobtang.sourcecompany.api.corp_detail.service.CorpDetailService;
import com.jobtang.sourcecompany.api.exception.CustomException;
import com.jobtang.sourcecompany.api.exception.ErrorCode;
import com.jobtang.sourcecompany.api.induty_detail.repository.IndutyDetailRepository;
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
    private final AnalysisResultService analysisResultService;
    private final CorpDetailService corpDetailService;
    private final IndutyDetailRepository indutyDetailRepository;
    private final CorpRepository corpRepository;
    private final ModelMapper mapper = new ModelMapper();
    private final RedisTemplate<String, CorpSearchListDto> redisTemplate;
    private final RedisTemplate<String, Integer> integerRedisTemplate;

    public List<CorpSearchListDto> searchCorp(String inputValue) {
        // 검색값 유효성 검사
        // 양쪽 공백제거, 특수문자(-공백) 제거
        inputValue = inputValue.trim().replaceAll("[^ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9-&]","");

        // 유효성 검사후 inputValue가 이제 없으면 검색 X
        if (inputValue.equals("")) {
            return new ArrayList<>();
        }
        // %value% 형식으로 LIKE 검색
        // totalview 기준으로 역순(내림차순) 정렬
        // Dto 형식대로 매핑
        return corpRepository.findByCorpNameContains(inputValue).stream()
                .sorted(Comparator.comparing(Corp::getTotalView).reversed())
                .map(c -> mapper.map(c, CorpSearchListDto.class))
                .collect(Collectors.toList());
    }

    public List<CorpAutoSearchDto> autoSearchCorp(String inputValue) {
        // 검색값 유효성 검사
        // 양쪽 공백제거, 특수문자(-공백) 제거
        inputValue = inputValue.trim().replaceAll("[^ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9-&]","");

        // 유효성 검사후 inputValue가 이제 없으면 검색 X
        if (inputValue.equals("")) {
            return new ArrayList<>();
        }
        // value% 형식으로 LIKE 검색
        // totalview 기준으로 역순(내림차순) 정렬
        // 5개만 추출
        return corpRepository.findByCorpNameStartingWith(inputValue).stream()
                .sorted(Comparator.comparing(Corp::getTotalView).reversed())
                .limit(5)
                .map(c -> mapper.map(c, CorpAutoSearchDto.class))
                .collect(Collectors.toList());
    }

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

    @Scheduled(cron = "0 0 0/3 * * ?") // 3시간마다 업데이트. 0,3,6,9,12,15,18,21시
    public void makeRandCorp() {
        // 랜덤 corp 만든거 전부 삭제
//        Set<String> keysSet = redisTemplate.keys("randcorp_*");
//        Iterator<String> keysIterator = keysSet.iterator();
//        while (keysIterator.hasNext()) {
//            redisTemplate.delete(keysIterator.next());
//        }

        // 모든 기업 가져오기
        List<Corp> corps = corpRepository.findAll();
        // 무작위로 셔플
        Collections.shuffle(corps);
        // 순회하면서 하나의 기업정보 객체를 CorpSearchListDto로 매핑해주고
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
        for (int i = (page-1)*10; i < page * 10 ; i++) {
            corpSearchListDtoList.add(redisTemplate.opsForValue().get("randcorp:"+i));
        }
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

    @Override
    public CorpListResponseDto getIndutyCorps() {
//        Pageable pageSetting = PageRequest.of(size, page);
//        List indutycodes = new ArrayList(List.of("A","B","C","D","E","F","G","H","I","J","K","L","M","N","P","R","S"));
        List indutycodes = new ArrayList(List.of("A","C","D","E","F","G","H","I","J","K","L","M","N","P","R"));

        Random random = new Random();
        String targetCode = String.valueOf(indutycodes.get(random.nextInt(indutycodes.size())));

//        Page<Corp> corps = corpRepository.findAllByIndutyCode(pageSetting, targetCode);
        List<Corp> corps = corpRepository.findAllByIndutyCode(targetCode);
        if (corps == null){throw new CustomException(ErrorCode.CORP_NOT_FOUND);}


        List<CorpSearchListDto> data = new ArrayList<>();
        for (Corp corp: corps) {
            data.add(mapper.map(corp, CorpSearchListDto.class));
        }
        return new CorpListResponseDto().builder()
                .kind(indutyDetailRepository.findByIndutyCode(targetCode).getIndutyName())
                .corps(data)
                .build();
    }

    @Override
    public CorpListResponseDto getGoodResultCorps(int size, int page) {
        return analysisResultService.GetGoodCorps(size, page);
    }

    @Override
    public CorpListResponseDto getTopSalesCorps(int size, int page) {
        return corpDetailService.getTopSalesCorps(size, page);
    }


}

