package com.jobtang.sourcecompany.api.corp_detail.util.Analysis.analysis_etc;

import com.jobtang.sourcecompany.api.corp.entity.Corp;
import com.jobtang.sourcecompany.api.corp.repository.CorpRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@Component
@NoArgsConstructor
public class AnalysisGpt {
    private RestTemplate restTemplate;
    private CorpRepository corpRepository;

    @Value("${drinks.milkshake}")
    private String API_KEY;
    private static final String ENDPOINT = "https://api.openai.com/v1/completions";

    public void reqGpt(String corpId) {
        Corp corp = corpRepository.findByCorpId(corpId);
        String askSentence = corp + " 100자로 설명해줘";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", "Bearer "+API_KEY);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.putAll(Map.of(
                "model", "gpt-3.5-turbo",
                "prompt", askSentence,
                "temperature", 0.7,
                "max_tokens,", 500
        ));

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, httpHeaders);

        ResponseEntity<Map> response = restTemplate.postForEntity(ENDPOINT, requestEntity, Map.class);
        System.out.println(response.toString());
    }

//    String  = corpId;

}
