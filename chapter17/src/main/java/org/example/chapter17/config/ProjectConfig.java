package org.example.chapter17.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.support.JpaEvaluationContextExtension;
import org.springframework.data.spel.spi.EvaluationContextExtension;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableMethodSecurity
public class ProjectConfig {

    @Bean
    public SecurityEvaluationContextExtension evaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();

        UserDetails readUser = User.withUsername("kim")
                .password("1234")
                .authorities("read")
                .build();

        UserDetails writeUser = User.withUsername("park")
                .password("1234")
                .authorities("write")
                .build();

        userDetailsService.createUser(readUser);
        userDetailsService.createUser(writeUser);

        return userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
