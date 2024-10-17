package com.grapeup.aibot.service;

import com.grapeup.aibot.adapters.GPTAdapter;
import com.grapeup.aibot.domain.SimilarityResults;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.List;
import java.util.Vector;

@AllArgsConstructor
public class QueryService {

    private final GPTAdapter gptAdapter;
    private final JdbcClient jdbcClient;

    public String complete(String query) {
        Vector<Double> queryEmbedding = gptAdapter.embedding(query);

        // Perform the vector similarity search
        JdbcClient.StatementSpec jdbcQuery = jdbcClient.sql(
                        "SELECT\n" +
                        "    documents.id,\n" +
                        "    documents.title,\n" +
                        "    documents.content,\n" +
                        "    1 - (documents.embedding <=> :query_embedding::vector) as similarity\n" +
                        "  FROM documents\n" +
                        "  WHERE documents.embedding <=> :query_embedding::vector < 1 - :threshold\n" +
                        "  ORDER BY documents.embedding <=> :query_embedding::vector\n" +
                        "  limit :limit;")
                .param("query_embedding", queryEmbedding.toString())
                .param("threshold", 0.8)
                .param("limit", 5);

        // Return the recommended places
        List<SimilarityResults> similarityResults = jdbcQuery.query(SimilarityResults.class).list();
        return gptAdapter.complete(query, similarityResults);
    }
}
