package de.comprot.facade

import de.comprot.core.repository.NoSuchUserException
import org.springframework.dao.DataAccessException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.orm.ObjectRetrievalFailureException
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
            ServletRequestBindingException.class ])
    def handleRequestException(Exception exception) {
        [ error: 'request error', cause: exception.message ]
    }

    @ResponseBody
    @RequestMapping(produces = Version.V1)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    def handleValidationException(ConstraintViolationException exception) {
        [   error: 'validation failure',
            violations: exception.constraintViolations.collect {[
                    value: it.invalidValue,
                    type: it.rootBeanClass.simpleName,
                    message: it.message
            ]}]
    }

    @ResponseBody
    @RequestMapping(produces = Version.V1)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    def handleValidationException(MethodArgumentNotValidException exception) {
        [   error: 'validation failure',
            violations: exception.bindingResult.allErrors.collect {[
                    target: exception.bindingResult.target,
                    type: exception.bindingResult.target.class.simpleName,
                    message: it.defaultMessage
            ]}]
    }

    @ResponseBody
    @RequestMapping(produces = Version.V1)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ObjectRetrievalFailureException.class)
    def handleValidationException(ObjectRetrievalFailureException exception) {
        [ error: 'entity not found', cause: exception.message ]
    }

    @ResponseBody
    @RequestMapping(produces = Version.V1)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    def handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        [ error: 'data integrity violation', cause: exception.cause.cause.localizedMessage ]
    }

    @ResponseBody
    @RequestMapping(produces = Version.V1)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataAccessException.class)
    def handleDataAccessException(DataAccessException exception) {
        [ error: 'data error', cause: exception.cause.message ]
    }

    @ResponseBody
    @RequestMapping(produces = Version.V1)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    def handleUnsupportedMediaTypeException(HttpMediaTypeNotSupportedException exception) {
        [   error: 'unsupported media type',
            cause: exception.localizedMessage,
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
    @ExceptionHandler(NoSuchUserException.class)
    def handle(NoSuchUserException exception) {
        [ message: 'no such user' ]
    }

}
