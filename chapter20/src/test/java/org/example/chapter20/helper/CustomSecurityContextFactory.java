package org.example.chapter20.helper;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public class CustomSecurityContextFactory implements WithSecurityContextFactory<WithCustomUser> {
    @Override
    public SecurityContext createSecurityContext(WithCustomUser annotation) {
        // 비어있는 보안 컨텍스트를 만든다.
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        // Authentication 인스턴스 생성
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(annotation.username(), null, null);

        // 모의 Authentication을 SecurityContext에 추가합니다.
        context.setAuthentication(token);

        return context;
    }
}
