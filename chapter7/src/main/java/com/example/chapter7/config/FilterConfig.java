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
        // WRITE, READ 권한 중 하나라도 있는 사용자는 요청 허용
        http.authorizeHttpRequests(authz -> authz.anyRequest().hasAnyAuthority("WRITE", "READ"))
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
