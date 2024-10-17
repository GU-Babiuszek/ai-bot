package com.grapeup.aibot;

import com.grapeup.aibot.service.DataService;
import com.grapeup.aibot.service.QueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
@Slf4j
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(QueryService queryService, DataService dataService) {
        return _ -> {
            // Prototyp, w projekcie tutaj mogłaby być na przykład integracja ze slackiem - zapytanie + odpowiedź
//            dataService.initializeData();
            while (true) {
                Scanner scanner = new Scanner(System.in);
                String query = scanner.nextLine();
                log.info("Answer: {}", queryService.complete(query));
            }
        };
    }
}