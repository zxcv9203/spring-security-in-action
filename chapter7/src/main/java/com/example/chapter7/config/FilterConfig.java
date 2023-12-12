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
        // WRITE 권한을 가진 사용자만 요청 성공
        http.authorizeHttpRequests(authz -> authz.anyRequest().hasAuthority("WRITE"))
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
