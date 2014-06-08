package de.comprot.controller.util

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.validation.BindException
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleBindException(
            BindException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {

        def body = []

        exception.fieldErrors.each {
            body += [
                field: it.field,
                message: it.defaultMessage
            ]
        }

        handleExceptionInternal(exception, body, headers, status, request);
    }
}
