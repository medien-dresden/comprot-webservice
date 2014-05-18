package de.comprot

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.springframework.stereotype.Component;

@Component
class SimpleCORSFilter implements Filter {

    @Override
    void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        response.setHeader('Access-Control-Allow-Origin', '*')
        chain.doFilter(request, response)
    }

    @Override
    void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    void destroy() {}

}