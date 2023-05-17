package com.jobtang.sourcecompany.api.corp_detail.service;

import com.jobtang.sourcecompany.api.analysis_result.service.AnalysisResultService;
import com.jobtang.sourcecompany.api.corp.entity.Corp;
import com.jobtang.sourcecompany.api.corp.repository.CorpRepository;
import com.jobtang.sourcecompany.api.corp_detail.document.AnalysisDocument;
import com.jobtang.sourcecompany.api.corp_detail.document.AnalysisGptDocument;
import com.jobtang.sourcecompany.api.corp_detail.document.AnalysisInfoDocument;
import com.jobtang.sourcecompany.api.corp_detail.dto.*;
import com.jobtang.sourcecompany.api.corp_detail.dto.analysis_etc.GptDataDto;
import com.jobtang.sourcecompany.api.corp_detail.dto.comparison.ComparisonResultDto;
import com.jobtang.sourcecompany.api.corp_detail.dto.comparison.CorpForComparisonDto;
import com.jobtang.sourcecompany.api.corp_detail.entity.CorpDetail;
import com.jobtang.sourcecompany.api.corp_detail.repository.AnalysisGptRepository;
import com.jobtang.sourcecompany.api.corp_detail.repository.AnalysisInfoRepository;
import com.jobtang.sourcecompany.api.corp_detail.repository.AnalysisRepository;
import com.jobtang.sourcecompany.api.corp_detail.repository.CorpDetailRepository;
import com.jobtang.sourcecompany.api.corp_detail.util.Analysis.DoAnalysis;
import com.jobtang.sourcecompany.api.corp_detail.util.Analysis.analysis_etc.AnalysisGpt;
import com.jobtang.sourcecompany.api.corp_detail.util.AnalysisInfo;
import com.jobtang.sourcecompany.api.corp_detail.util.BasicSetting;
import com.jobtang.sourcecompany.api.corp_detail.util.Calculator;
import com.jobtang.sourcecompany.api.corp_detail.util.variable.AnalysisVariable;
import com.jobtang.sourcecompany.api.exception.CustomException;
import com.jobtang.sourcecompany.api.exception.ErrorCode;
import com.jobtang.sourcecompany.api.induty_detail.entity.IndutyDetail;
import com.jobtang.sourcecompany.api.induty_detail.repository.IndutyDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnalysisServiceImpl implements AnalysisService {

    private final CorpDetailRepository corpDetailRepository;
    private final CorpRepository corpRepository;
    private final IndutyDetailRepository indutyDetailRepository;
    private final AnalysisGptRepository analysisGptRepository;
    private final MongoTemplate mongoTemplate;
    private final DoAnalysis doAnalysis;
    private final AnalysisRepository analysisRepository;
    private final AnalysisInfoRepository analysisInfoRepository;
    private final ModelMapper mapper;
    private final AnalysisResultService analysisResultService;
    private final AnalysisGpt analysisGpt;
    private final BasicSetting basicSetting;
    private final Calculator calculator;

    private AnalysisVariable analysisVariable;

    @Override
    public AnalysisResponseDto getCorpAnalysis(String analysisId, String corpId, int settingNum) {
        System.out.println(corpId);
        if (corpId.equals("66666666")) {
            corpId="00401731";
            Corp corp = corpRepository.findByCorpId(corpId);
            AnalysisDocument corpDocument = analysisRepository.findByVariableId(corpId);
            AnalysisDocument indutyDocument = analysisRepository.findByVariableId(corp.getIndutyCode());
            AnalysisInfoDocument analysisInfoDocument = analysisInfoRepository.findByAnalysisId(analysisId);

            if (corpDocument == null) {
                throw new CustomException(ErrorCode.CORP_NOT_FOUND);
            } else if (indutyDocument == null) {
                throw new CustomException(ErrorCode.INDUTY_NOT_FOUND);
            } else if (analysisInfoDocument == null) {
                throw new CustomException(ErrorCode.ANALYSIS_NOT_FOUND);
            }

            VariableDto corpVariableDto = mapper.map(corpDocument, VariableDto.class);
            VariableDto indutyVariableDto = mapper.map(indutyDocument, VariableDto.class);

            // 분석법 찾기
            AnalysisDto corpAnalysis = new AnalysisDto();
            for (AnalysisDto corpAnalysisDto : corpVariableDto.getData()) {
                if (corpAnalysisDto.getAnalysisId().equals(analysisId)) {
                    corpAnalysis = corpAnalysisDto;
                    break;
                }
            }
            AnalysisDto indutyAnalysis = new AnalysisDto();
            for (AnalysisDto indutyAnalysisDto : indutyVariableDto.getData()) {
                if (indutyAnalysisDto.getAnalysisId().equals(analysisId)) {
                    indutyAnalysis = indutyAnalysisDto;
                    break;
                }
            }
            List<HashMap> analysisResult = new ArrayList<>();
            try {
                for (AnalysisResultDto corpResultDto : corpAnalysis.getAnalysisResult()) {
                    for (AnalysisResultDto indutyResultDto : indutyAnalysis.getAnalysisResult()) {
                        if (corpResultDto.getName().equals(indutyResultDto.getName())) {
                            String rate = rate(analysisId, corpResultDto, indutyResultDto);
                            analysisResult.add(new HashMap(Map.of(
                                    corp.getCorpName(), calculator.myRound(corpResultDto.getValue()),
                                    "name", corpResultDto.getName(),
                                    "산업평균", calculator.myRound(indutyResultDto.getValue()),
                                    "평가", "불량"
                            )));

                            break;
                        }
                    }
                }
            } catch (Exception e) {}


            return new AnalysisResponseDto().builder()
                    .exist_all((corpAnalysis.getIsExistAll().equals(true) && indutyAnalysis.getIsExistAll().equals(true)) ? true : false)
                    .analysis_method(corpAnalysis.getAnalysisId())
                    .analysis_name(corpAnalysis.getAnalysisName())
                    .corp_id("66666666")
                    .corp_name("참깨전자")
                    .analysisInfo(analysisInfoDocument.getData())
                    .rate("불량")
                    .result(analysisResult)
                    .build();
        }
        Corp corp = corpRepository.findByCorpId(corpId);
        AnalysisDocument corpDocument = analysisRepository.findByVariableId(corpId);
        AnalysisDocument indutyDocument = analysisRepository.findByVariableId(corp.getIndutyCode());
        AnalysisInfoDocument analysisInfoDocument = analysisInfoRepository.findByAnalysisId(analysisId);

        if (corpDocument == null) {
            throw new CustomException(ErrorCode.CORP_NOT_FOUND);
        } else if (indutyDocument == null) {
            throw new CustomException(ErrorCode.INDUTY_NOT_FOUND);
        } else if (analysisInfoDocument == null) {
            throw new CustomException(ErrorCode.ANALYSIS_NOT_FOUND);
        }

        VariableDto corpVariableDto = mapper.map(corpDocument, VariableDto.class);
        VariableDto indutyVariableDto = mapper.map(indutyDocument, VariableDto.class);

        // 분석법 찾기
        AnalysisDto corpAnalysis = new AnalysisDto();
        for (AnalysisDto corpAnalysisDto : corpVariableDto.getData()) {
            if (corpAnalysisDto.getAnalysisId().equals(analysisId)) {
                corpAnalysis = corpAnalysisDto;
                break;
            }
        }
        AnalysisDto indutyAnalysis = new AnalysisDto();
        for (AnalysisDto indutyAnalysisDto : indutyVariableDto.getData()) {
            if (indutyAnalysisDto.getAnalysisId().equals(analysisId)) {
                indutyAnalysis = indutyAnalysisDto;
                break;
            }
        }
        List<HashMap> analysisResult = new ArrayList<>();
        int goodCnt = 0;
        String checkRate = "";
        // 같은 것 끼리 묶기
        if (analysisId=="114") {
            try {
                for (AnalysisResultDto corpResultDto : corpAnalysis.getAnalysisResult()) {
                    String rate = rate(analysisId, corpResultDto, corpResultDto);
                    if (rate.equals("고평가")) {
                        checkRate = "고평가";
                    } else if (rate.equals("저평가")) {
                        checkRate = "저평가";
                    } else if (rate.equals("양호")) {
                        goodCnt += 1;
                    }
                    analysisResult.add(new HashMap(Map.of(
                            corp.getCorpName(), calculator.myRound(corpResultDto.getValue()),
                            "name", corpResultDto.getName(),
                            "평가", rate
                    )));
            }} catch (Exception e) {}

        } else {

        try {
            for (AnalysisResultDto corpResultDto : corpAnalysis.getAnalysisResult()) {
                for (AnalysisResultDto indutyResultDto : indutyAnalysis.getAnalysisResult()) {
                    if (corpResultDto.getName().equals(indutyResultDto.getName())) {
                        String rate = rate(analysisId, corpResultDto, indutyResultDto);
                        if (rate.equals("고평가")) {
                            checkRate = "고평가";
                        } else if (rate.equals("저평가")) {
                            checkRate = "저평가";
                        } else if (rate.equals("양호")) {
                            goodCnt += 1;
                        }
                        analysisResult.add(new HashMap(Map.of(
                                corp.getCorpName(), calculator.myRound(corpResultDto.getValue()),
                                "name", corpResultDto.getName(),
                                "산업평균", calculator.myRound(indutyResultDto.getValue()),
                                "평가", rate
                        )));

                        break;
                    }
                }
            }
        } catch (Exception e) {}
        }
        // 종합 평가
        String totalRate = "";
        try {
            if (checkRate.length() > 1) {
                totalRate = checkRate;
                // 60% 이상이 양호이면 양호, 30% 이하는 불량, 그 외는 불량
            } else if (goodCnt >= corpAnalysis.getAnalysisResult().size() * 6 / 10) {
                totalRate = "양호";
            } else if (goodCnt < corpAnalysis.getAnalysisResult().size() * 6 / 10 ||
                    goodCnt >= corpAnalysis.getAnalysisResult().size() * 3 / 10) {
                totalRate = "보통";
            } else if (goodCnt < corpAnalysis.getAnalysisResult().size() * 3 / 10) {
                totalRate = "불량";
            } else {
                totalRate = "";
            }
        } catch (Exception e) {
        }

        if (settingNum == 1) {
            analysisResultService.updateAnalysisResult(corp, corpAnalysis.getAnalysisId(), totalRate);
            return null;
        }

        return new AnalysisResponseDto().builder()
                .exist_all((corpAnalysis.getIsExistAll().equals(true) && indutyAnalysis.getIsExistAll().equals(true)) ? true : false)
                .analysis_method(corpAnalysis.getAnalysisId())
                .analysis_name(corpAnalysis.getAnalysisName())
                .corp_id(corp.getCorpId())
                .corp_name(corp.getCorpName())
                .analysisInfo(analysisInfoDocument.getData())
                .rate(totalRate)
                .result(analysisResult)
                .build();
    }

    @Override
    public String getGptAnalysis(String corpId) {
        Corp corp = corpRepository.findByCorpId(corpId);
        if (corp == null) {
            throw new CustomException(ErrorCode.CORP_NOT_FOUND);
        }

        AnalysisGptDocument analysisGptDocument = analysisGptRepository.findByCorpId(corp.getCorpId()).orElseThrow(() -> new CustomException(ErrorCode.ANALYSIS_NOT_FOUND));
        return analysisGptDocument.getContent();
    }

    @Override
    public HashMap getCorpComparison(String corpIdA, String corpIdB) {
        Corp corpA = corpRepository.findByCorpId(corpIdA);
        Corp corpB = corpRepository.findByCorpId(corpIdB);
        if (corpA == null || corpB == null) {
            throw new CustomException(ErrorCode.CORP_NOT_FOUND);
        }

        AnalysisDocument corpADocument = analysisRepository.findByVariableId(corpIdA);
        AnalysisDocument corpBDocument = analysisRepository.findByVariableId(corpIdB);
        if (corpADocument == null || corpBDocument == null) {
            throw new CustomException(ErrorCode.ANALYSIS_NOT_FOUND);
        }

        VariableDto corpAVariableDto = mapper.map(corpADocument, VariableDto.class);
        VariableDto corpBVariableDto = mapper.map(corpBDocument, VariableDto.class);

        HashMap result = new HashMap();

        // 기업 기본 정보 입력
        result.putAll(Map.of(
                "corpA", mapper.map(corpA, CorpForComparisonDto.class),
                "corpB", mapper.map(corpB, CorpForComparisonDto.class)
        ));
        List data = new ArrayList();
        // 기업 비교 정보 입력
        for (String analysisId : basicSetting.getAnalysisIds()) {
            data.add(makeComparisonInfo(analysisId, corpAVariableDto, corpBVariableDto));
        }
        result.put("analysis", data);
        return result;
    }

    private ComparisonResultDto makeComparisonInfo(String analysisId, VariableDto corpAVariableDto, VariableDto corpBVariableDto) {
        AnalysisInfoDocument analysisInfoDocument = analysisInfoRepository.findByAnalysisId(analysisId);

        // 분석법 찾기
        AnalysisDto corpAAnalysis = new AnalysisDto();
        for (AnalysisDto corpAAnalysisDto : corpAVariableDto.getData()) {
            if (corpAAnalysisDto.getAnalysisId().equals(analysisId)) {
                corpAAnalysis = corpAAnalysisDto;
                break;
            }
        }
        AnalysisDto corpBAnalysis = new AnalysisDto();
        for (AnalysisDto corpBAnalysisDto : corpBVariableDto.getData()) {
            if (corpBAnalysisDto.getAnalysisId().equals(analysisId)) {
                corpBAnalysis = corpBAnalysisDto;
                break;
            }
        }
        List<HashMap> analysisResult = new ArrayList<>();

        // 같은 것 끼리 묶기
        try {
            for (AnalysisResultDto corpAResultDto : corpAAnalysis.getAnalysisResult()) {
                for (AnalysisResultDto corpBResultDto : corpBAnalysis.getAnalysisResult()) {
                    if (corpAResultDto.getName().equals(corpBResultDto.getName())) {
                        analysisResult.add(new HashMap(Map.of(
                                "name", corpAResultDto.getName(),
                                corpAVariableDto.getVariableName(), calculator.myRound(corpAResultDto.getValue()),
                                corpBVariableDto.getVariableName(), calculator.myRound(corpBResultDto.getValue())
                        )));
                        break;
                    }
                }
            }
        } catch (Exception e) {
        }

        return new ComparisonResultDto().builder()
                .analysis_name(analysisInfoDocument.getData().get("analysis_name"))
                .analysis_method(analysisInfoDocument.getData().get("analysis_id"))
                .exist_all((corpAAnalysis.getIsExistAll().equals(true) && corpBAnalysis.getIsExistAll().equals(true)) ? true : false)
                .result(analysisResult)
                .analysisInfo(analysisInfoDocument.getData())
                .build();
    }


    @Override
    public void updateAnalysisCorp(String variableId) {
        if (variableId.length() == 1) {
            IndutyDetail indutyDetail = indutyDetailRepository.findByIndutyCode(variableId);
            if (indutyDetail == null) {
                throw new CustomException(ErrorCode.CORP_NOT_FOUND);
            }
            analysisVariable = new AnalysisVariable(indutyDetail);
        } else {
            CorpDetail corpDetail = corpDetailRepository.findByCorpDetailId(variableId);
            if (corpDetail == null) {
                throw new CustomException(ErrorCode.CORP_NOT_FOUND);
            }
            analysisVariable = new AnalysisVariable(corpDetail);
        }

        VariableDto variableDto = doAnalysis.DoAnalysis(analysisVariable);

        // MongoDB에 저장하기
        try {
            AnalysisDocument analysisDocument = mapper.map(variableDto, AnalysisDocument.class);
            mongoTemplate.save(analysisDocument);
            log.info("분석 저장완료 : " + analysisVariable.variableName);
        } catch (Exception e) {
            log.info("분석 저장실패 : " + analysisVariable.variableName);
        }
    }

    @Override
    public void updateAnalysisAllCorp() {
        deleteAnalysisAllCorp();
        log.info("기존 기업 분석 데이터 삭제 완료!");

        List<String> corpIds = corpRepository.findAllCorpIds();
        for (String corpId : corpIds) {
            updateAnalysisCorp(corpId);
        }
        log.info("모든 기업 분석 완료!");

        List<String> indutyCodes = indutyDetailRepository.findAllIndutyCodes();
        for (String indutycode : indutyCodes) {
            updateAnalysisCorp(indutycode);
        }
        log.info("모든 산업 분석 완료!");

        // entity 저장
//        for (String corpId : corpIds) {
//            for (String analysisId : basicSetting.getAnalysisIds()) {
//                getCorpAnalysis(analysisId, corpId, 1);
//            }
//        }
//        log.info("모든 기업 분석결과 저장 완료!");
    }

    @Override
    public Boolean deleteAnalysisAllCorp() {
        try {
            mongoTemplate.remove(new Query(), AnalysisDocument.class);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public void updateAnalysisInfo() {
        AnalysisInfo analysisInfo = new AnalysisInfo();
        List<HashMap> dataList = analysisInfo.getInfo();
        mongoTemplate.remove(new Query(), AnalysisInfoDocument.class);

        // MongoDB에 저장하기
        for (HashMap<String, String> data : dataList) {
            AnalysisInfoDocument analysisInfoDocument = new AnalysisInfoDocument();
            String keyName = data.get("analysis_id");
            analysisInfoDocument.setAnalysisId(keyName);
            analysisInfoDocument.setData(data);
            mongoTemplate.save(analysisInfoDocument);

        }
        log.info("모든 기업분석 저장완료!");
    }

    private int updateAnalysisGpt(Corp corp) {
        if (analysisGptRepository.findByCorpId(corp.getCorpId()).isPresent()) {
            log.info("이미 존재 하므로 넘깁니다. " + corp.getCorpName());
            return 0;
        }

        GptDataDto gptDataDto = analysisGpt.reqGpt(corp.getCorpName());

        // MongoDB에 저장하기
        try {
            AnalysisGptDocument analysisGptDocument = new AnalysisGptDocument();
            analysisGptDocument.setCorpId(corp.getCorpId());
            analysisGptDocument.setCorpName(corp.getCorpName());
            analysisGptDocument.setContent(gptDataDto.getContent());
            mongoTemplate.save(analysisGptDocument);
            log.info("GPT 업데이트 완료 : " + corp.getCorpName());
            return gptDataDto.getUsedTokenNum();
        } catch (Exception e) {
            log.info("GPT 업데이트 실패 : " + corp.getCorpName());
            return 0;
        }
    }

    @Override
    public void updateAnalysisGptAll(int size) {

        Pageable pageSetting = PageRequest.of(size, 100);
        Page<Corp> corps = corpRepository.findAllByOrderByCorpId(pageSetting);
        if (corps == null) {
            throw new CustomException(ErrorCode.CORP_NOT_FOUND);
        }

        int usage = 0;

        for (Corp corp : corps) {
            System.out.println(corp.getCorpName());
            int value = updateAnalysisGpt(corp);
            usage += value;
            log.info("현 사용량 : " + value + " / 누적사용량 : " + usage + " / 회사명 : " + corp.getCorpName());
        }
        log.info("총 사용량 : " + usage + " / 총 금액" + usage / 1000 * 0.0002);
    }

    private String rate(String analysisId, AnalysisResultDto corpVariable, AnalysisResultDto indutyVariable) {
        switch (analysisId) {
            case "101":
                switch (corpVariable.getName()) {
                    case "유동비율":
                        return (corpVariable.getValue() >= 130) ? "양호" : "불량";
                    case "당좌비율":
                        return (corpVariable.getValue() >= 80) ? "양호" : "불량";
                    case "현금비율":
                        return (corpVariable.getValue() >= 20 || corpVariable.getValue() < 30) ? "양호" : "불량";
                    case "순운전자본비율":
                        return (corpVariable.getValue() >= indutyVariable.getValue()) ? "양호" : "불량";
                }
            case "103":
                switch (corpVariable.getName()) {
                    case "비유동비율":
                        return (corpVariable.getValue() <= indutyVariable.getValue()) ? "양호" : "불량";
                    case "비유동장기적합률":
                        return (corpVariable.getValue() <= 100) ? "양호" : "불량";
                }
            case "104":
                switch (corpVariable.getName()) {
                    case "유동자산구성비율":
                        return (corpVariable.getValue() >= indutyVariable.getValue() * 0.8 ||
                                corpVariable.getValue() <= indutyVariable.getValue() * 1.2) ? "양호" : "불량";
                    case "유형자산구성비율":
                        return (corpVariable.getValue() <= 100) ? "양호" : "불량";
                }
            case "109":
                return (corpVariable.getValue() >= indutyVariable.getValue()) ? "양호" : "불량";
            case "110":
                return (corpVariable.getValue() >= indutyVariable.getValue()) ? "고평가" : "저평가";
            case "111":
                return (corpVariable.getValue() >= indutyVariable.getValue()) ? "고평가" : "저평가";
            case "113":
                switch (corpVariable.getName()) {
                    case "ROI":
                        return (corpVariable.getValue() >= indutyVariable.getValue()) ? "양호" : "불량";
                    case "ROE":
                        return (corpVariable.getValue() >= indutyVariable.getValue()) ? "양호" : "불량";
                }
            case "405":
                String rate;
                if (corpVariable.getValue() <= 1.81) {
                    rate = "불량";
                } else if (corpVariable.getValue() <= 2.99 || corpVariable.getValue() > 1.81) {
                    rate = "보통";
                } else {
                    rate = "양호";
                }
                return rate;
            // 버전 2
            case "102":
                switch (corpVariable.getName()) {
                    case "부채비율":
                    case "차입금의존도":
                    case "차입금평균이자율":
                        return (corpVariable.getValue() < indutyVariable.getValue()) ? "양호" : "불량";
                    case "자기자본비율":
                    case "이자보상비율":
                    case "EBITDA/이자비용비율":
                        return (corpVariable.getValue() >= indutyVariable.getValue()) ? "양호" : "불량";
                }
            case "105":
                switch (corpVariable.getName()) {
                    case "유동비율":
                        return (corpVariable.getValue() >= 130) ? "양호" : "불량";
                    case "당좌비율":
                        return (corpVariable.getValue() >= 80) ? "양호" : "불량";
                    case "재고자산회전율":
                    case "총자산회전율":
                    case "매출액영업이익률":
                        return (corpVariable.getValue() >= indutyVariable.getValue()) ? (corpVariable.getValue() >= indutyVariable.getValue() * 0.8) ? "양호" : "보통" : "불량";
                    case "총자본순이익률(ROI)":
                        return (corpVariable.getValue() >= indutyVariable.getValue() * 0.8 ||
                                corpVariable.getValue() <= indutyVariable.getValue() * 1.2) ? "양호" : "불량";
                    case "총자산영업이익률(ROA)":
                    case "자기자본순이익률(ROE)":
                        return (corpVariable.getValue() >= indutyVariable.getValue()) ? "양호" : "불량";
                }
            case "106":
                switch (corpVariable.getName()) {
                    case "총자산회전율":
                    case "자기자본회전율":
                    case "비유동자산회전율":
                    case "재고자산회전율":
                    case "매출채권회전율":
                    case "매입채무회전율":
                        return (corpVariable.getValue() >= indutyVariable.getValue()) ? (corpVariable.getValue() >= indutyVariable.getValue() * 0.8) ? "양호" : "보통" : "불량";
                }
            case "108":
                switch (corpVariable.getName()) {
                    case "총자산회전율":
                    case "자기자본회전율":
                    case "비유동자산회전율":
                    case "재고자산회전율":
                    case "매출채권회전율":
                    case "매입채무회전율":
                        return (corpVariable.getValue() >= indutyVariable.getValue()) ? (corpVariable.getValue() >= indutyVariable.getValue() * 0.8) ? "양호" : "보통" : "불량";
                }
            case "114":
                switch (corpVariable.getName()) {
                    case "월의 지수법":
                    case "트랜드의 지수법":
                    case "브리체트의 지수법":
                        return (corpVariable.getValue() >= 100) ? "양호" : "불량";
                }
            case "303": return null;
            case "304": return null;
            case "408":
                return (corpVariable.getValue() >= 0.0380) ? "양호" : "불량";
                }

                return "몰루?";
        }

    }
