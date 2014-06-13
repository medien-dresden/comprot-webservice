package de.comprot

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@SuppressWarnings("GrMethodMayBeStatic")
@ControllerAdvice class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody def handle(MethodArgumentNotValidException exception) {
        exception.bindingResult.fieldErrors.collect {
            [ property: it.field, message: it.defaultMessage ]
        }
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody def handle(DataIntegrityViolationException exception) {
        [ message: 'data integrity violation' ]
    }

}
