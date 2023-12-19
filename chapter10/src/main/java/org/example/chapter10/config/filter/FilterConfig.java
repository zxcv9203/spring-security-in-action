package org.example.chapter10.config.filter;

import org.example.chapter10.repository.CustomCsrfTokenRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

@Configuration
public class FilterConfig {

    private final CustomCsrfTokenRepository csrfTokenRepository;

    public FilterConfig(CustomCsrfTokenRepository csrfTokenRepository) {
        this.csrfTokenRepository = csrfTokenRepository;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(c -> {
            c.csrfTokenRepository(csrfTokenRepository);
            // https://github.com/spring-projects/spring-security/issues/13599
            // 스프링 시큐리티 6.0 이상의 기본값인 XorCsrfTokenRequestAttributeHandler의 경우 Base64로 전달받은 토큰 값을 디코딩하기 때문에 발생
            // Base64로 디코딩 받는 이유는 HTML 요청으로 받기 때문에 인코딩된 CSRF 요청을 받기 때문입니다.
            // 인코딩되지 않는 경우에는 CsrfTokenRequestAttributeHandler()를 사용하여 해결해야 합니다.
            c.csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler());
            c.ignoringRequestMatchers("/hi");
        });
        http.authorizeHttpRequests(authz -> authz.anyRequest().permitAll());

        return http.build();
    }
}
