package com.jobtang.sourcecompany.api.analysis_result.service;

import com.jobtang.sourcecompany.api.analysis_result.entity.AnalysisResult;
import com.jobtang.sourcecompany.api.analysis_result.repository.AnalysisResultRepository;
import com.jobtang.sourcecompany.api.corp.dto.CorpListResponseDto;
import com.jobtang.sourcecompany.api.corp.dto.CorpSearchListDto;
import com.jobtang.sourcecompany.api.corp.entity.Corp;
import com.jobtang.sourcecompany.api.corp.repository.CorpRepository;
import com.jobtang.sourcecompany.api.corp_detail.repository.AnalysisInfoRepository;
import com.jobtang.sourcecompany.api.corp_detail.util.BasicSetting;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnalysisResultServiceImpl implements AnalysisResultService{

    private final AnalysisInfoRepository analysisInfoRepository;
    private final AnalysisResultRepository analysisResultRepository;
    private final CorpRepository corpRepository;
    private final ModelMapper mapper;
    private final BasicSetting basicSetting;

    @Override
    public void updateAnalysisResult(Corp corp, String analysisId, String rate) {
        AnalysisResult analysisResult = analysisResultRepository.findByCorp(corp);
        if (analysisResult == null) {
            analysisResult = new AnalysisResult();
            analysisResult.setCorp(corpRepository.findByCorpId(corp.getCorpId()));
        }
        analysisResult.updateResult(analysisId, rate);
        analysisResultRepository.save(analysisResult);
    }

    @Override
    public CorpListResponseDto GetGoodCorps(int size, int page) {
        Pageable pageSetting = PageRequest.of(size, page);
        List analysisIds = basicSetting.getAnalysisIdsForListing();

        Random random = new Random();
        String targetAnalysisId = String.valueOf(analysisIds.get(random.nextInt(analysisIds.size())));
        Page<AnalysisResult> analysisResults = null;
        switch (targetAnalysisId) {
            case "101" : analysisResults = analysisResultRepository.findALlByResult101(pageSetting, "양호"); break;
            case "103" : analysisResults = analysisResultRepository.findALlByResult103(pageSetting, "양호"); break;
            case "104" : analysisResults = analysisResultRepository.findALlByResult104(pageSetting, "양호"); break;
            case "109" : analysisResults = analysisResultRepository.findALlByResult109(pageSetting, "양호"); break;
            case "113" : analysisResults = analysisResultRepository.findALlByResult113(pageSetting, "양호"); break;
            case "405" : analysisResults = analysisResultRepository.findALlByResult405(pageSetting, "양호"); break;

            case "102" : analysisResults = analysisResultRepository.findALlByResult102(pageSetting, "양호"); break;
            case "105" : analysisResults = analysisResultRepository.findALlByResult105(pageSetting, "양호"); break;
            case "106" : analysisResults = analysisResultRepository.findALlByResult106(pageSetting, "양호"); break;
            case "108" : analysisResults = analysisResultRepository.findALlByResult108(pageSetting, "양호"); break;
            case "114" : analysisResults = analysisResultRepository.findALlByResult114(pageSetting, "양호"); break;
            case "303" : analysisResults = analysisResultRepository.findALlByResult303(pageSetting, "양호"); break;
            case "408" : analysisResults = analysisResultRepository.findALlByResult408(pageSetting, "양호"); break;
        }
        List<CorpSearchListDto> data = new ArrayList<>();
        for (AnalysisResult analysisResult : analysisResults) {
            data.add(mapper.map(analysisResult.getCorp(), CorpSearchListDto.class));
        }
        return new CorpListResponseDto().builder()
                .kind(analysisInfoRepository.findByAnalysisId(targetAnalysisId).getData().get("analysis_name"))
                .corps(data)
                .build();
    }
}
