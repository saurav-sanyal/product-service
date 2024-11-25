package com.whitewolf.product.appconfig;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import org.slf4j.MDC;

import java.io.IOException;
import java.util.UUID;

public class MDCFilter implements Filter {

    private static final String UUID_KEY = "uuid";

    @Override
    public void doFilter(jakarta.servlet.ServletRequest request,
                         jakarta.servlet.ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        try {
            // Generate a unique UUID for each request
            String requestId = UUID.randomUUID().toString();
            MDC.put(UUID_KEY, requestId);
            chain.doFilter(request, response);
        } finally {
            MDC.clear(); // Prevent memory leaks
        }
    }
}

