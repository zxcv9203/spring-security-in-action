package com.example.chapter7.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class FilterConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // CustomAuthorizationManager에서 통과하는 경우 액세스 허용
        http.authorizeHttpRequests(authz -> authz.anyRequest().access(new CustomAuthorizationManager()))
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
