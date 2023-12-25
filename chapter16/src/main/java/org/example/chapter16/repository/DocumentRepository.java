package org.example.chapter16.repository;

import org.example.chapter16.model.Document;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class DocumentRepository {

    private Map<String, Document> documents = Map.of(
            "abc123", new Document("kim"),
            "qwe123", new Document("kim"),
            "asd5555", new Document("park")
    );

    public Document findByCode(String code) {
        return documents.get(code);
    }
}
