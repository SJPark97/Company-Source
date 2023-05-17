package com.jobtang.sourcecompany.api.corp_detail.util;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BasicSetting {
    public List<String> getAnalysisIds(){
        return new ArrayList<>(List.of(
                 "101"
                ,"103"
                ,"104"
                ,"109"
                ,"110"
                ,"111"
                ,"113"
                ,"405"
            // 버전2
                ,"102"
                ,"105"
                ,"106"
                ,"108"
                ,"114"
                ,"303"
                ,"304"
                ,"408"
        ));
    }

    public List<String> getAnalysisIdsForListing(){
        return new ArrayList<>(List.of(
                "101"
//                ,"103"
                ,"104"
//                ,"109"
//                ,"110"
                ,"111"
                ,"113"
                ,"405"

                // 버전2
                ,"102"
                ,"105"
                ,"106"
//                ,"108"
                ,"114"
//                ,"303"
//                ,"304"
                ,"408"
        ));
    }
}