package org.example.business.security.config;

import org.example.business.security.auth.OtpAuthenticationProvider;
import org.example.business.security.auth.UsernamePasswordAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;

import java.util.List;

@Configuration
public class AuthenticationConfig {

    private final OtpAuthenticationProvider otpAuthenticationProvider;

    private final UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;

    public AuthenticationConfig(OtpAuthenticationProvider otpAuthenticationProvider, UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider) {
        this.otpAuthenticationProvider = otpAuthenticationProvider;
        this.usernamePasswordAuthenticationProvider = usernamePasswordAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(
                List.of(otpAuthenticationProvider, usernamePasswordAuthenticationProvider)
        );
    }
}
