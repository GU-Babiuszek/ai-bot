package com.grapeup.aibot.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Vector;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OpenAIEmbeddingResponse {

    private String object;
    private List<Data> data;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Data {
        private String object;
        private Integer index;
        private Vector<Double> embedding;
    }
}
