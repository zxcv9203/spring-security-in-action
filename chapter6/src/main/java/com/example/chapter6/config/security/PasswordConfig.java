package com.example.chapter6.config.security;

import com.example.chapter6.domain.user.model.type.EncryptionAlgorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.example.chapter6.domain.user.model.type.EncryptionAlgorithm.BCRYPT;
import static com.example.chapter6.domain.user.model.type.EncryptionAlgorithm.SCRYPT;

@Configuration
public class PasswordConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        Map<String, PasswordEncoder> encoders = new HashMap<>();

        encoders.put(BCRYPT.name().toLowerCase(Locale.ROOT), new BCryptPasswordEncoder());
        encoders.put(SCRYPT.name().toLowerCase(Locale.ROOT), new SCryptPasswordEncoder(16384, 8, 1, 32, 64));
        return new DelegatingPasswordEncoder(BCRYPT.name().toLowerCase(Locale.ROOT), encoders);
    }

}
