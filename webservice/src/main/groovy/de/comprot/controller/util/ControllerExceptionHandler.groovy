package de.comprot.controller.util

import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.validation.BindException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    def handleBindException(BindException exception) {
        new HttpEntity<Object>(exception.fieldErrors.collect {[
            field: it.field,
            message: it.defaultMessage
        ]})
    }

}
