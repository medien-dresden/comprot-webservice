package de.comprot.facade

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component class RestAuthEntryPoint implements AuthenticationEntryPoint {

    @Override void commence(HttpServletRequest request,
                            HttpServletResponse response, AuthenticationException authException) {
        response.status = HttpServletResponse.SC_UNAUTHORIZED
    }

}
