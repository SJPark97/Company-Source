package com.jobtang.sourcecompany.api.analysis_result.entity;

import com.jobtang.sourcecompany.api.corp.entity.Corp;
import com.jobtang.sourcecompany.util.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class AnalysisResult extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String analysisResultId;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "corp_id")
    private Corp corp;

    private String result101;
    private String result103;
    private String result104;
    private String result109;
    private String result110;
    private String result111;
    private String result113;
    private String result405;

    public void updateResult(String analysisId, String rate){
        switch (analysisId) {
            case "101": this.result101 = rate; break;
            case "103": this.result103 = rate; break;
            case "104": this.result104 = rate; break;
            case "109": this.result109 = rate; break;
            case "110": this.result110 = rate; break;
            case "111": this.result111 = rate; break;
            case "113": this.result113 = rate; break;
            case "405": this.result405 = rate; break;
        }
    }
}
