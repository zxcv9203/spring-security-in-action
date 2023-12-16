package org.example.chapter9.config.security.filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class AuthenticationLogginFilter implements Filter {
    private final Logger logger = LoggerFactory.getLogger(AuthenticationLogginFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String requestId = httpRequest.getHeader("Request-Id"); // 요청헤더에서 ID를 얻음

        logger.info("Successfully authenticated request with id " + requestId); // 요청 ID의 값과 이벤트 기록

        chain.doFilter(request, response);
    }
}
