package com.grapeup.aibot.domain;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OpenAICompletionRequestBody {
    private String model;
    private List<Message> messages;
    private Double temperature;

    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public static class Message {
        private String role;
        private String content;
    }
}
