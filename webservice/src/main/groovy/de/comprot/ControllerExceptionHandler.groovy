package de.comprot

import de.comprot.model.EntityFieldError
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.validation.BindException
import org.springframework.web.bind.annotation.ExceptionHandler

@SuppressWarnings("GrMethodMayBeStatic")
@ControllerAdvice class ControllerExceptionHandler {

    @ExceptionHandler(BindException.class)
    def handle(BindException exception) {
        new ResponseEntity<List<EntityFieldError>>(exception.fieldErrors.collect {
                new EntityFieldError(field: it.field, message: it.defaultMessage)
        }, HttpStatus.BAD_REQUEST)
    }

}
