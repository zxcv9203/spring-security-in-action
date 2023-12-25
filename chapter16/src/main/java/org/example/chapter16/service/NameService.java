package org.example.chapter16.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class NameService {

    @PreAuthorize("hasAuthority('write')")
    public String getName() {
        return "writeUser";
    }
}
