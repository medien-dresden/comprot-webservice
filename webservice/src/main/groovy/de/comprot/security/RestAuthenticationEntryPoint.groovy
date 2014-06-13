package de.comprot.security

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override void commence(HttpServletRequest request,
                            HttpServletResponse response, AuthenticationException authException) {

        response.setContentType("application/json")
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED)
        response.getOutputStream().println('{"message":"unauthorized"}')
    }

}