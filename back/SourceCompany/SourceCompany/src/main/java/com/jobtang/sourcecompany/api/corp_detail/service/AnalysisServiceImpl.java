package com.jobtang.sourcecompany.api.corp_detail.service;

import com.jobtang.sourcecompany.api.analysis_result.service.AnalysisResultService;
import com.jobtang.sourcecompany.api.corp.entity.Corp;
import com.jobtang.sourcecompany.api.corp.repository.CorpRepository;
import com.jobtang.sourcecompany.api.corp_detail.document.AnalysisDocument;
import com.jobtang.sourcecompany.api.corp_detail.document.AnalysisInfoDocument;
import com.jobtang.sourcecompany.api.corp_detail.dto.AnalysisDto;
import com.jobtang.sourcecompany.api.corp_detail.dto.AnalysisResponseDto;
import com.jobtang.sourcecompany.api.corp_detail.dto.AnalysisResultDto;
import com.jobtang.sourcecompany.api.corp_detail.dto.VariableDto;
import com.jobtang.sourcecompany.api.corp_detail.entity.CorpDetail;
import com.jobtang.sourcecompany.api.corp_detail.repository.AnalysisInfoRepository;
import com.jobtang.sourcecompany.api.corp_detail.repository.AnalysisRepository;
import com.jobtang.sourcecompany.api.corp_detail.repository.CorpDetailRepository;
import com.jobtang.sourcecompany.api.corp_detail.util.Analysis.DoAnalysis;
import com.jobtang.sourcecompany.api.corp_detail.util.Analysis.analysis_etc.AnalysisGpt;
import com.jobtang.sourcecompany.api.corp_detail.util.AnalysisInfo;
import com.jobtang.sourcecompany.api.corp_detail.util.variable.AnalysisVariable;
import com.jobtang.sourcecompany.api.exception.CustomException;
import com.jobtang.sourcecompany.api.exception.ErrorCode;
import com.jobtang.sourcecompany.api.induty_detail.entity.IndutyDetail;
import com.jobtang.sourcecompany.api.induty_detail.repository.IndutyDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnalysisServiceImpl implements AnalysisService{

    private final CorpDetailRepository corpDetailRepository;
    private final CorpRepository corpRepository;
    private final IndutyDetailRepository indutyDetailRepository;
    private final MongoTemplate mongoTemplate;
    private final DoAnalysis doAnalysis;
    private final AnalysisRepository analysisRepository;
    private final AnalysisInfoRepository analysisInfoRepository;
    private final ModelMapper mapper;
    private final AnalysisResultService analysisResultService;

    private AnalysisVariable analysisVariable;

    @Override
    public void updateAnalysisCorp(String variableId) {
        if (variableId.length() == 1) {
            IndutyDetail indutyDetail = indutyDetailRepository.findByIndutyCode(variableId);
            if (indutyDetail == null) {
                throw new CustomException(ErrorCode.CORP_NOT_FOUND);
            }
            analysisVariable = new AnalysisVariable(indutyDetail);
        }
        else {
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
        for (String corpId : corpIds) {
            for (String analysisId : new ArrayList<>(List.of(
                    "101", "103", "104", "109", "110", "111", "113"
            ))) {
                getCorpAnalysis(analysisId, corpId, 1);
            }
        }
        log.info("모든 기업 분석 완료!");
    }

    @Override
    public AnalysisResponseDto getCorpAnalysis(String analysisId, String corpId, int settingNum) {
        Corp corp = corpRepository.findByCorpId(corpId);
        AnalysisDocument corpDocument = analysisRepository.findByVariableId(corpId);
        AnalysisDocument indutyDocument = analysisRepository.findByVariableId(corp.getIndutyCode());
        AnalysisInfoDocument analysisInfoDocument = analysisInfoRepository.findByAnalysisId(analysisId);

        if (corpDocument == null) {throw new CustomException(ErrorCode.CORP_NOT_FOUND);}
        else if (indutyDocument == null) {throw new CustomException(ErrorCode.INDUTY_NOT_FOUND);}
        else if (analysisInfoDocument == null) {throw new CustomException(ErrorCode.ANALYSIS_NOT_FOUND);}

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
        try {
            for (AnalysisResultDto corpResultDto : corpAnalysis.getAnalysisResult()) {
                for (AnalysisResultDto indutyResultDto : indutyAnalysis.getAnalysisResult()) {
                    if (corpResultDto.getName().equals(indutyResultDto.getName())) {
                        String rate = rate(analysisId, corpResultDto, indutyResultDto);
                        if (rate.equals("고평가")) {checkRate = "고평가";}
                        else if (rate.equals("저평가")) {checkRate = "저평가";}
                        else if (rate.equals("양호")) {goodCnt += 1;}
                        analysisResult.add(new HashMap(Map.of(
                                corp.getCorpName(),corpResultDto.getValue(),
                                "name", corpResultDto.getName(),
                                "산업평균", indutyResultDto.getValue(),
                                "평가", rate
                        )));

                        break;
                    }
                }
            }
        } catch (Exception e) {}
        // 종합 평가
        String totalRate = "";
        try{
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
            } else {totalRate = "";}
        } catch (Exception e) {}

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

        return ;
    }

    @Override
    public void updateAnalysisGpt(String corpId) {
        System.out.println("GPT 요청 들어옴");
        AnalysisGpt analysisGpt = new AnalysisGpt();
        analysisGpt.reqGpt(corpId);
    }

    private String rate(String analysisId, AnalysisResultDto corpVariable, AnalysisResultDto indutyVariable) {
        switch (analysisId) {
            case "101" :
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
            case "103" :
                switch (corpVariable.getName()) {
                    case "비유동비율":
                        return (corpVariable.getValue() <= indutyVariable.getValue()) ? "양호" : "불량";
                    case "비유동장기적합률":
                        return (corpVariable.getValue() <= 100) ? "양호" : "불량";
                }
            case "104" :
                switch (corpVariable.getName()) {
                    case "유동자산구성비율":
                        return (corpVariable.getValue() >= indutyVariable.getValue() * 0.8 ||
                                corpVariable.getValue() <= indutyVariable.getValue() * 1.2) ? "양호" : "불량";
                    case "유형자산구성비율":
                        return (corpVariable.getValue() <= 100) ? "양호" : "불량";
                }
            case "109" :
                return (corpVariable.getValue() >= indutyVariable.getValue()) ? "양호" : "불량";
            case "110" :
                return (corpVariable.getValue() >= indutyVariable.getValue()) ? "고평가" : "저평가";
            case "111" :
                return (corpVariable.getValue() >= indutyVariable.getValue()) ? "고평가" : "저평가";
            case "113" :
                switch (corpVariable.getName()) {
                    case "ROI분석":
                        return (corpVariable.getValue() >= indutyVariable.getValue()) ? "고평가" : "저평가";
                    case "ROE분석":
                        return (corpVariable.getValue() >= indutyVariable.getValue()) ? "고평가" : "저평가";
                }
            case "405" :
                String rate;
                if (corpVariable.getValue() <= 1.81) {rate = "불량";}
                else if (corpVariable.getValue() <= 2.99 || corpVariable.getValue() > 1.81) {rate = "보통";}
                else {rate = "양호";}
                return rate;
            }
        return "몰루?";
        }
}
