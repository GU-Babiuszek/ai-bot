package com.grapeup.aibot.configuration;

import com.grapeup.aibot.adapters.GPTAdapter;
import com.grapeup.aibot.repository.DocumentRepository;
import com.grapeup.aibot.service.DataService;
import com.grapeup.aibot.service.QueryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.simple.JdbcClient;

@Configuration
public class AIBotConfiguration {

    @Bean
    public DataService dataService(GPTAdapter gptAdapter, DocumentRepository documentRepository) {
        return new DataService(gptAdapter, documentRepository);
    }

    @Bean
    public QueryService queryService(GPTAdapter gptAdapter, JdbcClient jdbcClient) {
        return new QueryService(gptAdapter, jdbcClient);
    }
}
