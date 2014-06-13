package de.comprot

import de.comprot.model.EntityValidationError
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.validation.BindException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@SuppressWarnings("GrMethodMayBeStatic")
@ControllerAdvice class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    @ResponseBody def handle(BindException exception) {
        exception.fieldErrors.collect { new EntityValidationError(field: it.field, message: it.defaultMessage) }
    }

}
