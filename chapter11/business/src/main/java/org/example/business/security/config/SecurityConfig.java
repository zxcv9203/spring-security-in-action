package org.example.business.security.config;

import org.example.business.security.filter.InitialAuthenticationFilter;
import org.example.business.security.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final InitialAuthenticationFilter initialAuthenticationFilter;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final AuthenticationManager authenticationManager;

    public SecurityConfig(InitialAuthenticationFilter initialAuthenticationFilter, JwtAuthenticationFilter jwtAuthenticationFilter, AuthenticationManager authenticationManager) {
        this.initialAuthenticationFilter = initialAuthenticationFilter;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.authenticationManager = authenticationManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
                .authenticationManager(authenticationManager)
                .addFilterAt(initialAuthenticationFilter, BasicAuthenticationFilter.class)
                .addFilterAfter(jwtAuthenticationFilter, BasicAuthenticationFilter.class)
                .authorizeHttpRequests(authz -> authz.anyRequest().authenticated());
        return http.build();
    }
}
