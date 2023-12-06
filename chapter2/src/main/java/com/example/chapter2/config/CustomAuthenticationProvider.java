package com.example.chapter2.config;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // Principal 인터페이스의 getName() 메서드를 Authentication에서 상속 받습니다.
        String username = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());

        // 일반적으로는 이러한 방식 대신 UserDetailsService 및 PasswordEncoder를 호출해서
        // 사용자 이름과 암호를 테스트합니다.
        if ("kim".equals(username) && "12345".equals(password)) {
            return new UsernamePasswordAuthenticationToken(username, password, List.of());
        } else {
            throw new AuthenticationCredentialsNotFoundException("인증 오류가 발생했습니다.");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class
                .isAssignableFrom(authentication);
    }
}
