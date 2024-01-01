package org.example.chapter20.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NameService {

    private final Map<String, List<String>> secretNames = Map.of(
            "kim", List.of("k", "i", "m"),
            "park", List.of("p", "a", "r", "k")
    );

    @PreAuthorize("hasAuthority('write')")
    public String getName() {
        return "writeUser";
    }

    @PreAuthorize("#name == authentication.principal.username")
    public List<String> getSecretNames(String name) {
        return secretNames.get(name);
    }
}