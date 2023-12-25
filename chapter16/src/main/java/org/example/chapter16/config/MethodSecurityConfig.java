package org.example.chapter16.config;

import org.example.chapter16.validator.DocumentPermissionEvaluator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Configuration
@EnableMethodSecurity
public class MethodSecurityConfig {

    private final DocumentPermissionEvaluator evaluator;

    public MethodSecurityConfig(DocumentPermissionEvaluator evaluator) {
        this.evaluator = evaluator;
    }

    @Bean
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        DefaultMethodSecurityExpressionHandler handler = new DefaultMethodSecurityExpressionHandler();

        handler.setPermissionEvaluator(evaluator);

        return handler;
    }

}
