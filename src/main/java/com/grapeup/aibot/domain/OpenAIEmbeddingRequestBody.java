package com.grapeup.aibot.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OpenAIEmbeddingRequestBody {

    private String model;
    private String input;
}
