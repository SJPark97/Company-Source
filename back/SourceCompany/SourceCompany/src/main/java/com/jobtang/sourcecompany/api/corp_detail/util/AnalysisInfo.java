package com.jobtang.sourcecompany.api.corp_detail.util;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class AnalysisInfo {

    public List<HashMap> getInfo(){
        List<HashMap> result = new ArrayList<>();

        //유동성분석 101
        HashMap data101 = new HashMap<String, String>();

        data101.put("analysis_id", "101");
        data101.put("analysis_name", "유동성분석");
        data101.put("analysis_description",
                "기업이 얼마나 현금화를 잘 할 수 있나를 측정하기 위한 비율"
                );
        data101.put("analysis_detail",
                "1. 개념\n" +
                "    1. 6가치 중심의 경영분석 제4판- 김철중 저"
                );
        result.add(data101);

        //자본배분의 안전성 분석 103
        HashMap data103 = new HashMap<String, String>();

        data103.put("analysis_id", "103");
        data103.put("analysis_name", "자본배분의 안전성 분석");
        data103.put("analysis_description",
                "1. 조달된 자본이 기업의 자산에 얼마나 적절히 배분되고 있는지를 측정하는 비율\n" +
                "2. 기업이 장기적으로 경기변동, 시장여건 변화 등 경제변화에 잘 적응할 수 있는지를 검토하기 위한 비율"
        );
        data103.put("analysis_detail",
                "1. 개념\n" +
                "    1. 조달된 자본이 기업의 자산에 얼마나 적절히 배분되고 있는지를 측정하는 비율\n" +
                "    2. 기업이 장기적으로 경기변동, 시장여건 변화 등 경제변화에 잘 적응할 수 있는지를 검토하기 위한 비율\n" +
                "2. 해석법\n" +
                "    1. 비유동비율(고정비율) : 일반적인 기준은 100% 이하, 산업간 큰차이가 있음\n" +
                "    2. 비유동장기적합률(고정장기적합률) : 100% 이하여야함.(장기자본=자기자본+비유동부채 범위 내에서 이루어져야하기 때문)\n" +
                "3. 계산법\n" +
                "    1. 비유동비율 = 비유동자산/자기자본 * 100\n" +
                "    2. 비유동장기적합률 = 비유동자산/(자기자본+비유동부채)*100\n" +
                "4. 출처\n" +
                "    1. 도서) 기업가치 중심의 경영분석 제4판- 김철중 저"
        );
        result.add(data103);


        return result;
    }
}
//[
//        {
//        "analysisId" : "101",
//        "analysisName" : "유동성분석",
//        "analysisDescription" : "대충 요약 내용",
//        "analysisDetail" : "대충 디테일한 내용"
//        }
//        ]