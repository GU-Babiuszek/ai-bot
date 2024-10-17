package com.grapeup.aibot.service;

import com.grapeup.aibot.adapters.GPTAdapter;
import com.grapeup.aibot.adapters.OpenAIRestAdapter;
import com.grapeup.aibot.jpa.Document;
import com.grapeup.aibot.repository.DocumentRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

@AllArgsConstructor
@Slf4j
public class DataService {

    private final GPTAdapter gptAdapter;
    private final DocumentRepository repository;

    public void initializeData() {
        repository.deleteAll();
        Stream.of(new File("./documents").listFiles())
                .filter(file -> !file.isDirectory())
                .map(f -> {
                    String content = getFileContents(f);
                    return Document.builder()
                            .title(f.getName())
                            .content(content)
                            .embedding(gptAdapter.embedding(content))
                            .build();
                })
                .forEach(repository::save);
    }

    public String getFileContents(File file) {
        try {
            return Files.readString(file.toPath());
        } catch (IOException e) {
            log.error("Encountered error while reading file \"{}\"", file.getAbsolutePath(), e);
            return "";
        }
    }
}
