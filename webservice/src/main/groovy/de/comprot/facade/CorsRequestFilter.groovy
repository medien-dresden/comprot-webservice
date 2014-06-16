package de.comprot.facade

import org.springframework.stereotype.Component
import javax.servlet.Filter

import javax.servlet.FilterChain
import javax.servlet.FilterConfig
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse

@Component class CorsRequestFilter implements Filter {

    @Override void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        response.setHeader('Access-Control-Allow-Origin', '*')
        response.setHeader('Access-Control-Allow-Methods', 'GET, POST, PUT, DELETE')
        response.setHeader('Access-Control-Allow-Headers', 'Content-Type')

        chain.doFilter(request, response);
    }

    @Override void init(FilterConfig filterConfig) {}
    @Override void destroy() {}

}