package com.example.chapter7.config;

import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

import java.util.function.Supplier;

public class CustomAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {
    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
        Authentication auth = authentication.get();

        // "WRITE" 권한을 가진 사용자에게만 접근을 허용
        boolean isWritable = auth.getAuthorities().stream()
                .anyMatch(grantedAuthority -> "WRITE".equals(grantedAuthority.getAuthority()));

        return new AuthorizationDecision(isWritable);
    }
}
