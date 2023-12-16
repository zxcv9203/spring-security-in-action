package org.example.chapter10.config.filter;

import jakarta.servlet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.csrf.CsrfToken;

import java.io.IOException;

public class CsrfTokenLogger implements Filter {

    private Logger logger = LoggerFactory.getLogger(CsrfTokenLogger.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Object o = request.getAttribute("_csrf");
        CsrfToken token = (CsrfToken) o;

        logger.info("CSRF Token : {}", token.getToken());
        chain.doFilter(request, response);
    }
}
