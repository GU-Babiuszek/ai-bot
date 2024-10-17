package com.grapeup.aibot.configuration;

import com.grapeup.aibot.adapters.OpenAIRestAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.socket.WebSocketHttpHeaders;

@Configuration
public class OpenAIRestConfiguration {

    @Bean
    public RestClient restClient(@Value("${OPENAI_API_KEY}") String openAIAPIKey) {
        return RestClient.builder()
                .requestFactory(new HttpComponentsClientHttpRequestFactory())
                .defaultHeader(WebSocketHttpHeaders.AUTHORIZATION, "Bearer " + openAIAPIKey)
                .defaultHeader("OpenAI-Beta", "realtime=v1")
                .build();
    }

    @Bean
    public OpenAIRestAdapter openAIRestAdapter(RestClient restClient) {
        return new OpenAIRestAdapter(restClient);
    }
}
