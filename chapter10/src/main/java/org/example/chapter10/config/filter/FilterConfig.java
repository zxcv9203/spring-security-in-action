package org.example.chapter10.config.filter;

import org.example.chapter10.repository.CustomCsrfTokenRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class FilterConfig {

    private final CustomCsrfTokenRepository csrfTokenRepository;

    public FilterConfig(CustomCsrfTokenRepository csrfTokenRepository) {
        this.csrfTokenRepository = csrfTokenRepository;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(c -> {
            c.csrfTokenRepository(csrfTokenRepository);
            c.ignoringRequestMatchers("/hi");
            c.ignoringRequestMatchers("/h2-console/**");
        });
        http.headers().frameOptions().disable();
        http.authorizeHttpRequests(authz -> authz.anyRequest().permitAll());

        return http.build();
    }
}
