package org.example.chapter16.service;

import org.example.chapter16.model.Document;
import org.example.chapter16.repository.DocumentRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @PostAuthorize("hasPermission(returnObject, 'ROLE_admin')")
    public Document getDocument(String code) {
        return documentRepository.findByCode(code);
    }

    @PreAuthorize("hasPermission(#code, 'document', 'ROLE_admin')")
    public Document getDocumentPre(String code) {
        return documentRepository.findByCode(code);
    }
}
