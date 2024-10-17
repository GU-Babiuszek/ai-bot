package com.grapeup.aibot.adapters;

import com.grapeup.aibot.domain.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@AllArgsConstructor
@Slf4j
public class OpenAIRestAdapter implements GPTAdapter {

    private static final String OPENAI_URI = "https://api.openai.com";

    private final RestClient restClient;

    @Override
    public Vector<Double> embedding(String query) {
        log.info("Getting embedding for query [{}]", query);
        OpenAIEmbeddingRequestBody body = new OpenAIEmbeddingRequestBody("text-embedding-ada-002", query);
        return restClient.post()
                .uri(OPENAI_URI + "/v1/embeddings")
                .body(body)
                .exchange((request, response) -> {
                    log.info("Got embedding for query [{}]", query);
                    return response.bodyTo(OpenAIEmbeddingResponse.class).getData().get(0).getEmbedding();
                });
    }

    @Override
    public String complete(String query, List<SimilarityResults> similarityResults) {
        log.info("Completing query [{}] with {} files", query, similarityResults.size());
        List<OpenAICompletionRequestBody.Message> messages = new ArrayList<>();
        messages.add(new OpenAICompletionRequestBody.Message("system", "You are an assistant that is supposed to help someone out with queries related to Games Workshop warhammer world. You will be given files by assistant, you should only use the knowledge from these files to answer the query, and you should always link the filename. Assistant will link you the file together with its content in the following format: [FILENAME]:[CONTENT]"));
        messages.add(new OpenAICompletionRequestBody.Message("user", query));
        for (SimilarityResults result : similarityResults) {
            messages.add(new OpenAICompletionRequestBody.Message("assistant", "[" + result.getTitle() + "]:[" + result.getContent() + "]"));
        }
        OpenAICompletionRequestBody body = new OpenAICompletionRequestBody("gpt-4o-mini", messages, 0.7);
        return restClient.post()
                .uri(OPENAI_URI + "/v1/chat/completions")
                .body(body)
                .exchange((httpRequest, httpResponse) -> {
                    OpenAICompletionResponse completionResponse = httpResponse.bodyTo(OpenAICompletionResponse.class);
                    String completionMessage = completionResponse.getChoices().get(0).getMessage().getContent();
                    log.info("Got completion response for query [{}]: {}", query, completionMessage);
                    return completionMessage;
                });
    }
}
