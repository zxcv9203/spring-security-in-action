package org.example.business.security.filter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.business.security.auth.OtpAuthentication;
import org.example.business.security.auth.UsernamePasswordAuthentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class InitialAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationManager manager;

    @Value("${jwt.signing.key}")
    private String signingKey;

    public InitialAuthenticationFilter(AuthenticationManager manager) {
        this.manager = manager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String username = request.getHeader("username");
        String password = request.getHeader("password");
        String code = request.getHeader("code");
        if (code == null) {
            // OTP 코드가 null이라면 첫번째 인증단계로 판단
            Authentication auth = new UsernamePasswordAuthentication(username, password);
            manager.authenticate(auth);
        } else {
            // OTP 코드가 null이 아니라면 두번째 인증단계로 판단
            Authentication auth = new OtpAuthentication(username, code);

            manager.authenticate(auth);

            SecretKey key = Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));

            // JWT를 구축하고 인증된 사용자의 사용자 이름을 클레임으로 지정합니다.
            String jwt = Jwts.builder()
                    .claim("username", username)
                    .signWith(key)
                    .compact();

            // 토큰을 HTTP 응답의 권한 부여 헤더에 추가합니다.
            response.setHeader("Authorization", jwt);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        // /login 경로에만 해당 필터 적용
        return !"/login".equals(request.getServletPath());
    }
}
