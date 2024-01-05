package org.example.chapter20.security;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());

        if ("kim".equals(username) && "12345".equals(password)) {
            return new UsernamePasswordAuthenticationToken(username, password, List.of(new SimpleGrantedAuthority("write")));
        } else if ("mary".equals(username) && "12345".equals(password)) {
            return new UsernamePasswordAuthenticationToken(username, password, List.of(new SimpleGrantedAuthority("read")));
        }
        else {
            throw new AuthenticationCredentialsNotFoundException("인증 오류가 발생했습니다.");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class
                .isAssignableFrom(authentication);
    }
}
