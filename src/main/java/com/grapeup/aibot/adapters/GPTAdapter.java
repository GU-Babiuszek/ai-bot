package com.grapeup.aibot.adapters;

import com.grapeup.aibot.domain.SimilarityResults;

import java.util.List;
import java.util.Vector;

public interface GPTAdapter {
    Vector<Double> embedding(String query);
    String complete(String query, List<SimilarityResults> similarityResults);
}
