package org.example.chapter10.config.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class FilterConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(c -> c.ignoringRequestMatchers("/hi"));

        http.authorizeHttpRequests(authz -> authz.anyRequest().permitAll());

        return http.build();
    }
}
