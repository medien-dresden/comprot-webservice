package de.comprot.facade

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException
import de.comprot.core.repository.NoSuchEntityException
import de.comprot.core.repository.NoSuchUserException
import org.springframework.dao.DataAccessException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.orm.ObjectRetrievalFailureException
import org.springframework.web.HttpMediaTypeNotAcceptableException
import org.springframework.web.HttpMediaTypeNotSupportedException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.ServletRequestBindingException
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException
import org.springframework.web.bind.annotation.*

import javax.validation.ConstraintViolationException

@SuppressWarnings(["GrMethodMayBeStatic", "GroovyUnusedDeclaration"])
@ControllerAdvice class ControllerExceptionHandler {

    @ResponseBody
    @RequestMapping(produces = Version.V1)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler([MissingServletRequestParameterException.class,
            UnsatisfiedServletRequestParameterException.class,
            HttpRequestMethodNotSupportedException.class,
            ServletRequestBindingException.class,
            HttpMessageNotReadableException.class])
    def handle(Exception exception) {
        [ error: 'request error', cause: exception.message ]
    }

    @ResponseBody
    @RequestMapping(produces = Version.V1)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    def handle(ConstraintViolationException exception) {
        [   error: 'validation failure',
            violations: exception.constraintViolations.collect {[
                    message: it.message
            ]}]
    }

    @ResponseBody
    @RequestMapping(produces = Version.V1)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    def handle(MethodArgumentNotValidException exception) {
        [   error: 'validation failure',
            violations: exception.bindingResult.fieldErrors.collect {[
                    field: it.field,
                    message: it.defaultMessage
            ]}]
    }

    @ResponseBody
    @RequestMapping(produces = Version.V1)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ObjectRetrievalFailureException.class)
    def handle(ObjectRetrievalFailureException exception) {
        [ error: 'entity not found', cause: exception.message ]
    }

    @ResponseBody
    @RequestMapping(produces = Version.V1)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    def handle(DataIntegrityViolationException exception) {
        [ error: 'data integrity violation', cause: exception.cause.cause.localizedMessage ]
    }

    @ResponseBody
    @RequestMapping(produces = Version.V1)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataAccessException.class)
    def handle(DataAccessException exception) {
        [ error: 'data error', cause: exception.cause.message ]
    }

    @ResponseBody
    @RequestMapping(produces = Version.V1)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    def handle(HttpMediaTypeNotSupportedException exception) {
        [   error: 'unsupported media type',
            cause: exception.localizedMessage,
            supported: exception.supportedMediaTypes.collect({ (String) "${it.type}/${it.subtype}" })]
    }

    @ResponseBody
    @RequestMapping(produces = Version.V1)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    def handle(HttpMediaTypeNotAcceptableException exception) {
        [   error: 'unsupported media type',
            cause: exception.message,
            supported: exception.supportedMediaTypes.collect({ (String) "${it.type}/${it.subtype}" })]
    }

    @ResponseBody
    @RequestMapping(produces = Version.V1)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    def handleUncaughtException(Exception exception) {
        [ error: 'unknown error', cause: exception.message ]
    }

    @ResponseBody
    @RequestMapping(produces = Version.V1)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchEntityException.class)
    def handle(NoSuchEntityException exception) {
        [ error: 'no such entity' ]
    }

}
