package com.jobtang.sourcecompany;

import com.jobtang.sourcecompany.api.corp.entity.Corp;
import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.shaded.json.parser.JSONParser;
import com.nimbusds.jose.shaded.json.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;


@SpringBootTest
class SourceCompanyApplicationTests {

    @Test
    void dartParser() throws IOException, ParseException {
        RestTemplate restTemplate = new RestTemplate();


        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        String urlTemplate = UriComponentsBuilder.fromHttpUrl("https://opendart.fss.or.kr/api/fnlttSinglAcntAll.json")
                .queryParam("crtfc_key", "2d7b924b371dc7e4f7a5eebab0fd8a362f6cddd3")
                .queryParam("corp_code", "00126380")
                .queryParam("bsns_year", "2022")
                .queryParam("reprt_code", "11011")
                .queryParam("fs_div", "OFS")
                .encode()
                .toUriString();

        Map<String,String> params = new HashMap<>();
        params.put("crtfc_key", "2d7b924b371dc7e4f7a5eebab0fd8a362f6cddd3");
        params.put("corp_code", "00126380");
        params.put("bsns_year", "2022");
        params.put("reprt_code", "11011");
        params.put("fs_div", "OFS");

        HttpEntity<String> response = restTemplate.exchange(
                urlTemplate,
                HttpMethod.GET,
                entity,
                String.class,
                params
        );


        JSONParser parser = new JSONParser();
        Object obj = parser.parse(response.getBody());
        JSONObject jsonObject = (JSONObject)obj;
        JSONArray jsonArray = (JSONArray)jsonObject.get("list");
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject a = (JSONObject) jsonArray.get(i);
//            if (a.get("account_nm").equals("유동자산")) {
//                System.out.println("유동자산은 " + a.get("thstrm_amount"));
//            } else if (a.get("account_nm").equals("유동부채")) {
//                System.out.println("유동부채는 " + a.get("thstrm_amount"));
//            } else if (a.get("account_nm").equals("자산총계")) {
//                System.out.println("자산총계는 " + a.get("thstrm_amount"));
//            } else if (a.get("account_nm").equals("이익잉여금")) {
//                System.out.println("이익잉여금은 " + a.get("thstrm_amount"));
//            } else if (a.get("account_nm").equals("당기순이익")) {
//                System.out.println("당기순이익은 " + a.get("thstrm_amount"));
//            } else if (a.get("account_nm").equals("비유동자산")) {
//                System.out.println("비유동자산은 " + a.get("thstrm_amount"));
//            } else if (a.get("account_nm").equals("비유동부채")) {
//                System.out.println("비유동부채는 " + a.get("thstrm_amount"));
//            } else if (a.get("account_nm").equals("수익(매출액)")) {
//                System.out.println("전기매출액은 " + a.get("frmtrm_amount"));
//            } else if (a.get("account_nm").equals("자산총계")) {
//                System.out.println("전기총자산은 " + a.get("frmtrm_amount"));
//            } else if (a.get("account_nm").equals("당기순이익")) {
//                System.out.println("2년전 순이익 " + a.get("bfefrmtrm_amount"));
//            } else if (a.get("account_nm").equals("매출액")) {
//                System.out.println("매출액은 " + a.get("thstrm_amount"));
//            } else if (a.get("account_nm").equals("부채총계")) {
//                System.out.println("부채총계는 " + a.get("thstrm_amount"));
//            }
            System.out.println(a);
        }
//        System.out.println(response.getBody());


//        Reader reader = new FileReader("./dart.json");
//        JSONParser parser = new JSONParser();
//        Object obj = parser.parse(reader);
//        JSONArray jsonArray = (JSONArray)obj;
//        if (jsonArray.size() > 0) {
//            for (int i = 0; i < jsonArray.size(); i++) {
//                Corp corp = new Corp();
//                JSONObject jsonObj = (JSONObject) jsonArray.get(i);
//                JSONArray list = (JSONArray) jsonObj.get("list");
//                // 재무 재표만 stream 리스트 뽑기
//                corp.setCorpName((String) jsonObj.get("corp_name"));

                // 각각 필요한
//                if (list.size() > 0) {
//                    for (int j = 0; j < jsonArray.size(); j++) {
//                        JSONObject data = (JSONObject) list.get(i);
//                        if (data.get("fs_div") != "CFS") {
//
//                        }
//                    }
//                }
            }
        }

