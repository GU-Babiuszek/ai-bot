package com.grapeup.aibot.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SimilarityResults {
    private Integer id;
    private String title;
    private String content;
    private Double similarity;
}
