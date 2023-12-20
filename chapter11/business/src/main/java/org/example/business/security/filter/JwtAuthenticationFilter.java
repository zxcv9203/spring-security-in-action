package org.example.business.security.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.business.security.auth.UsernamePasswordAuthentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Value("${jwt.signing.key}")
    private String signingKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = request.getHeader("Authorization");

        SecretKey key = Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));

        // 토큰을 구문 분석해 클레임을 얻고 서명을 검증 (서명이 유효하지 않으면 예외 발생)
        // deprecated로 인해 다음 코드로 변경
        // https://dev.to/hieuit96bk/spring-boot-3-and-java-17-migration-guide-b8f
        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(jwt)
                .getPayload();

        String username = String.valueOf(claims.get("username"));

        // securityContext에 추가할 Authentication 인스턴스 생성
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("user");
        UsernamePasswordAuthentication auth = new UsernamePasswordAuthentication(username, null, List.of(grantedAuthority));

        SecurityContextHolder.getContext()
                .setAuthentication(auth);
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        // /login 경로에 대한 요청에는 트리거 되지 않도록 구성
        return "/login".equals(request.getServletPath());
    }
}
