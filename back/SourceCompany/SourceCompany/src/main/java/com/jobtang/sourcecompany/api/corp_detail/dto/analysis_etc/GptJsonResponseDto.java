package com.jobtang.sourcecompany.api.corp_detail.dto.analysis_etc;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class GptJsonResponseDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("object")
    private String object;

    @JsonProperty("created")
    private Long created;

    @JsonProperty("model")
    private String model;

    @JsonProperty("usage")
    private Usage usage;

    @JsonProperty("choices")
    private List<Choice> choices;

    @Getter
    @Setter
    public static class Usage {

        @JsonProperty("prompt_tokens")
        private Integer promptTokens;

        @JsonProperty("completion_tokens")
        private Integer completionTokens;

        @JsonProperty("total_tokens")
        private Integer totalTokens;
    }

    @Getter
    @Setter
    public static class Choice {

        @JsonProperty("message")
        private Message message;

        @JsonProperty("finish_reason")
        private String finishReason;

        @JsonProperty("index")
        private Integer index;

        @Getter
        @Setter
        public static class Message {

            @JsonProperty("role")
            private String role;

            @JsonProperty("content")
            private String content;
        }
    }
}
