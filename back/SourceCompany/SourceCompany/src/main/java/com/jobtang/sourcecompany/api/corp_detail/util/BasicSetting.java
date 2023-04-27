package com.jobtang.sourcecompany.api.corp_detail.util;

import com.jobtang.sourcecompany.api.corp.entity.Corp;
import com.jobtang.sourcecompany.api.corp_detail.entity.CorpDetail;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class BasicSetting {

    public HashMap basicSettings(Corp corp, HashMap data) {
        HashMap result = new HashMap();
        result.put("corpName", corp.getCorpName());
        result.put("analysis", data);
        return new HashMap(Map.of(corp.getCorpId(), result));
    }
}
//                {"00578321":{
//                corpName:"잡탕기업",
//                analysis:{"301":{},
//                "201":{},
//                "101":{"status101":true,
//                "rate":"보통",
//                "analysis_name":"유동성 분석",
//                "corp_id":"00425351",
//                "analysis_method":101,
//                "data101":[
//                {
//                "잡탕마을":151.3,
//                "name":"유동비율",
//                "산업평균":130.2,
//                "평가":"양호"
//                },
//                {
//                "잡탕마을":55.1,
//                "name":"당좌비율",
//                "산업평균":80.4,
//                "평가":"불량"
//                },
//                {
//                "잡탕마을":9.6,
//                "name":"현금비율",
//                "산업평균":20,
//                "평가":"불량"
//                },
//                {
//                "잡탕마을":19,
//                "name":"순운전자본비율",
//                "산업평균":10,
//                "평가":"양호"
//                }
//                ],
//                }
//                }
//                }
//                }
