package com.grapeup.aibot.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OpenAICompletionResponse {
    private String id;
    private String object;
    private Long created;
    private String model;
    private List<Choice> choices;
    private Usage usage;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Choice {
        private Integer index;
        private Message message;
        private String logprobs;
        private String finish_reason;

        @AllArgsConstructor
        @NoArgsConstructor
        @Getter
        @Setter
        public static class Message {
            private String role;
            private String content;
            private String refusal;
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Usage {
        private Integer prompt_tokens;
        private Integer completion_tokens;
        private Integer total_tokens;
        private Details prompt_tokens_details;
        private Details completion_tokens_details;

        @AllArgsConstructor
        @NoArgsConstructor
        @Getter
        @Setter
        public static class Details {
            private Integer cached_tokens;
            private Integer reasoning_tokens;
        }
    }
}
