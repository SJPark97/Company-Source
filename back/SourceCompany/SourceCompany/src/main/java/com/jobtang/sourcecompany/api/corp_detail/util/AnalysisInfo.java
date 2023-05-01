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

        //자산구성 분석 104
        HashMap data104 = new HashMap<String, String>();

        data104.put("analysis_id", "104");
        data104.put("analysis_name", "자산구성 분석");
        data104.put("analysis_description",
                "1. 유동산이 총자산 중에서 차지하는 비율\n" +
                "2. 유동자산구성비율이 높을수록 기업의 단기채무 변제능력이 높아짐"
        );
        data104.put("analysis_detail",
                "1. 개념\n" +
                "    1. 유동산이 총자산 중에서 차지하는 비율\n" +
                "    2. 유동자산구성비율이 높을수록 기업의 단기채무 변제능력이 높아짐\n" +
                "2. 해석법\n" +
                "    1. 산업 종류에따라 적정수준이 다름\n" +
                "    2. <아래는 적정수준을 Compan-Source에서 임의로 정의한 것>\n" +
                "    3. 유동자산구성비율 : 산업평균의 +- 20%, 양호, 그 외 보통\n" +
                "    4. 유형자산구성비율 : 산업평균의 +- 20%, 양호, 그 외 보통\n" +
                "3. 계산법\n" +
                "    1. 유동자산구성비율 = 유동자산/총자산 * 00\n" +
                "    2. 유형자산구성비율 = 유형자산/총자산 * 100\n" +
                "4. 출처\n" +
                "    1. 도서) 기업가치 중심의 경영분석 제4판- 김철중 저"
        );
        result.add(data104);


        //주가이익비율(PER) 분석 109
        HashMap data109 = new HashMap<String, String>();

        data109.put("analysis_id", "109");
        data109.put("analysis_name", "주가이익비율(PER)");
        data109.put("analysis_description",
                "1. 주가수익비율이라고도 하며, 실제주가를 주당이익으로 나눈 것\n" +
                "2. 주당이익이 평균일 때 주당이익이 높은 경우, 기업의 장래성이 좋으며, 성장하는 기업이다."
        );
        data109.put("analysis_detail",
                "1. 개념\n" +
                "    1. 주가수익비율이라고도 하며, 실제주가를 주당이익으로 나눈 것\n" +
                "    2. 주당이익이 평균일 때 주당이익이 높은 경우, 기업의 장래성이 좋으며, 성장하는 기업이다.\n" +
                "2. 해석법\n" +
                "    1. 주가이익비율(PER)이 크다는 것은 기업의 미래 성장가능성이 높게 평가\n" +
                "    2. <아래는 적정수준을 Compan-Source에서 임의로 정의한 것>\n" +
                "    3. 주가이익비율 : 동종업종보다 높으면 양호, 그 외 보통\n" +
                "3. 계산법\n" +
                "    1. 주가이익비율(PER) = 실제주가/주당이익 = 시가총액/순이익\n" +
                "4. 출처\n" +
                "    1. 도서) 기업가치 중심의 경영분석 제4판- 김철중 저"
        );
        result.add(data109);


        //주가순자산비율(PBR) 분석 110
        HashMap data110 = new HashMap<String, String>();

        data110.put("analysis_id", "110");
        data110.put("analysis_name", "주가순자산비율(PBR)");
        data110.put("analysis_description",
                "1. 주가순자산비율(PBR)은 기업가치를 평가하는 승수로 이용되기도 하고, 주가의 적정성 여부를 판단하는 기준으로도 이용됨"
        );
        data110.put("analysis_detail",
                "1. 개념\n" +
                "    1. 주가순자산비율(PBR)은 기업가치를 평가하는 승수로 이용되기도 하고, 주가의 적정성 여부를 판단하는 기준으로도 이용됨\n" +
                "2. 해석법\n" +
                "    1. PBR이 동종업계 평균보다 낮다면\n" +
                "        1. 저평가 되어있거나\n" +
                "        2. 적정평가이지만 성장가능성이 동종업계보다 열등하다.\n" +
                "3. 계산법\n" +
                "    1. 주가순자산비율(PBR) = 시가총액 / 자기자본\n" +
                "4. 출처\n" +
                "    1. 도서) 기업가치 중심의 경영분석 제4판- 김철중 저"
        );
        result.add(data110);



        //주가매출액비율(PSR) 분석 111
        HashMap data111 = new HashMap<String, String>();

        data111.put("analysis_id", "111");
        data111.put("analysis_name", "주가매출액비율(PSR)");
        data111.put("analysis_description",
                "1. 주가매출액비율(PSR)은 기업가치를 평가하는 승수로 이용되거나 주가의 적정성 여부를 판단하는 기준으로 이용됨.\n" +
                "2. 주가매출액비율(PSR)은 주가와 주당매출액을 비교하는 비율\n" +
                "3. 최근들어 벤처기업의 가치평가에서 PSR에 대한 관심이 증가하고 있음"
        );
        data111.put("analysis_detail",
                "1. 개념\n" +
                "    1. 주가매출액비율(PSR)은 기업가치를 평가하는 승수로 이용되거나 주가의 적정성 여부를 판단하는 기준으로 이용됨.\n" +
                "    2. 주가매출액비율(PSR)은 주가와 주당매출액을 비교하는 비율\n" +
                "    3. 최근들어 벤처기업의 가치평가에서 PSR에 대한 관심이 증가하고 있음\n" +
                "2. 해석법\n" +
                "    1. PSR이 동종업계 평균보다 낮다면\n" +
                "        1. 저평가 되어있거나\n" +
                "        2. 적정평가이지만 성장가능성이 동종업계보다 열등하다.\n" +
                "3. 계산법\n" +
                "    1. 주가매출액비율(PSR) = 실제주가/주당매출액 = 시가총액/매출액\n" +
                "4. 출처\n" +
                "    1. 도서) 기업가치 중심의 경영분석 제4판- 김철중 저"
        );
        result.add(data111);


        //ROI 분석 113
        HashMap data113 = new HashMap<String, String>();

        data113.put("analysis_id", "113");
        data113.put("analysis_name", "ROI 분석");
        data113.put("analysis_description",
                "1. ROI분석은 기업의 재무 및 경영성과를 일목요연하게 파악할 수 있는 대표적 분석으로 미국 듀퐁사가 개발한 이후 기업의 재무통제 및 종합적 재무분석에 널리 활용되고 있음\n" +
                "2. ROI기법은 주주에게 귀속되는 자기자본순이익률(ROE)을 기준으로 한 평가에서 연장되어 활용될 수 있음"
        );
        data113.put("analysis_detail",
                "1. 개념\n" +
                "    1. ROI분석은 기업의 재무 및 경영성과를 일목요연하게 파악할 수 있는 대표적 분석으로 미국 듀퐁사가 개발한 이후 기업의 재무통제 및 종합적 재무분석에 널리 활용되고 있음\n" +
                "    2. ROI기법은 주주에게 귀속되는 자기자본순이익률(ROE)을 기준으로 한 평가에서 연장되어 활용될 수 있음\n" +
                "2. 해석법\n" +
                "    1. ROI이 산업평균보다 높으면 양호, 아니면 불량(개선할 여지가 있음)\n" +
                "    2. ROI와 같음\n" +
                "3. 계산법\n" +
                "    1. ROI = 순이익/매출액 * 매출액/총자산 = 매출액순이익률 * 총자산회전율 = 매출마진 * 총자산회전속도\n" +
                "    2. ROE = ROI * (1+부채비율)\n" +
                "4. 출처\n" +
                "    1. 도서) 기업가치 중심의 경영분석 제4판- 김철중 저"
        );
        result.add(data113);

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