package com.example.chapter6.domain.user.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EncryptionAlgorithm {
    BCRYPT("{bcrypt}"),
    SCRYPT("{scrypt}");

    private final String prefix;
}
