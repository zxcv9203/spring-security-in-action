package org.example.chapter16.validator;

import org.example.chapter16.model.Document;
import org.example.chapter16.repository.DocumentRepository;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class DocumentPermissionEvaluator implements PermissionEvaluator {

    private final DocumentRepository documentRepository;

    public DocumentPermissionEvaluator(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        Document document = (Document) targetDomainObject;
        String p = (String) permission;

        boolean isAdmin = authentication.getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals(p));

        return isAdmin || document.getOwner().equals(authentication.getName());
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        String code = targetId.toString();
        Document document = documentRepository.findByCode(code);

        String p = (String) permission;

        boolean admin = authentication.getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals(p));

        return admin || document.getOwner().equals(authentication.getName());
    }
}
