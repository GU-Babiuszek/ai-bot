package com.grapeup.aibot.repository;

import com.grapeup.aibot.jpa.Document;
import org.springframework.data.repository.CrudRepository;

public interface DocumentRepository extends CrudRepository<Document, Long> {
}
