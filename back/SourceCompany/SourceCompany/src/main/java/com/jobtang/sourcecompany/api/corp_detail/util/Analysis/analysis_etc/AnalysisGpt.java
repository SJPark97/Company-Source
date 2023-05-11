package com.jobtang.sourcecompany.api.corp_detail.util.Analysis.analysis_etc;

import com.jobtang.sourcecompany.api.corp.entity.Corp;
import com.jobtang.sourcecompany.api.corp.repository.CorpRepository;
import com.jobtang.sourcecompany.api.corp_detail.dto.analysis_etc.GptDataDto;
import com.jobtang.sourcecompany.api.corp_detail.dto.analysis_etc.GptJsonResponseDto;
import com.jobtang.sourcecompany.api.exception.CustomException;
import com.jobtang.sourcecompany.api.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
@Slf4j
@RequiredArgsConstructor
public class AnalysisGpt {

    @Value("${drinks.milkshake}")
    private String API_KEY;
    private static final String ENDPOINT = "https://api.openai.com/v1/chat/completions";

    public GptDataDto reqGpt(String corpName) {
        String content = "기업 '" + corpName + "' 300자 내외로 설명해줘";
        HashMap innerMap = new HashMap<>();
        innerMap.putAll(Map.of(
                "role", "user",
                "content", content
        ));
        List<HashMap> message = new ArrayList<>();
        message.add(innerMap);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", "Bearer " + API_KEY);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.putAll(Map.of(
                "model", "gpt-3.5-turbo",
                "messages", message,
                "temperature", 0.7
        ));
        try {
            HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, httpHeaders);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<GptJsonResponseDto> response = restTemplate.postForEntity(ENDPOINT, requestEntity, GptJsonResponseDto.class);
            return new GptDataDto(response.getBody().getUsage().getTotalTokens(), response.getBody().getChoices().get(0).getMessage().getContent());
        } catch (Exception e) {
            log.warn("GPT 요청 실패");
            return null;
        }
    }
}
