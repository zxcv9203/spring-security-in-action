package org.example.chapter9.config.security.filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class AuthenticationLogginFilter extends OncePerRequestFilter {
    private final Logger logger = LoggerFactory.getLogger(AuthenticationLogginFilter.class);

    // OncePerRequestFilter는 HTTP 필터만 지원합니다.
    // 이 때문에 HttpServletRequest 및 HttpServletResponse로 매개 변수를 직접 지정했습니다.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestId = request.getHeader("Request-Id");

        logger.info("Successfully authentication request with id " + requestId);

        filterChain.doFilter(request, response);
    }
}
