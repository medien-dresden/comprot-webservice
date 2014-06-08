package de.comprot.controller.util

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.springframework.stereotype.Component;

@Component class SimpleCorsFilter implements Filter {

    @Override
    void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        response.setHeader('Access-Control-Allow-Origin', '*')
        response.setHeader('Access-Control-Allow-Methods', 'OPTIONS, GET, POST, PUT, DELETE')
        response.setHeader('Access-Control-Max-Age', '3600')

        chain.doFilter(request, response)
    }

    @Override
    void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    void destroy() {}

}