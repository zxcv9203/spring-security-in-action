package org.example.chapter8.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class FilterConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(authz -> authz
                        .requestMatchers("/hello").hasRole("ADMIN")
                        .requestMatchers("/hi").hasRole("DEVELOPER")
                        .requestMatchers(HttpMethod.GET, "/a").authenticated()
                        .requestMatchers(HttpMethod.POST, "/a").permitAll()
                        .requestMatchers( "/a/b/**").permitAll()
                        .anyRequest().denyAll())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
