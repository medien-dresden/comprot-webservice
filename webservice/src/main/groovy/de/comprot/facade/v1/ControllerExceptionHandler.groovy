package de.comprot.facade.v1

import de.comprot.core.service.EntityPropertyConstraintException
import de.comprot.core.service.JobSchedulingException
import de.comprot.core.service.NoSuchEntityException
import de.comprot.facade.Version
import org.springframework.dao.DataAccessException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.orm.ObjectRetrievalFailureException
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.HttpMediaTypeNotAcceptableException
import org.springframework.web.HttpMediaTypeNotSupportedException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.ServletRequestBindingException
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException

import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

import javax.validation.ConstraintViolationException

@SuppressWarnings(["GrMethodMayBeStatic", "GroovyUnusedDeclaration"])
@ControllerAdvice class ControllerExceptionHandler {

    @ResponseBody
    @RequestMapping(produces = Version.V1)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler([MissingServletRequestParameterException,
            UnsatisfiedServletRequestParameterException,
            HttpRequestMethodNotSupportedException,
            ServletRequestBindingException,
            HttpMessageNotReadableException,
            JobSchedulingException])
    def handle(Exception exception) {
        [ error: 'request error', cause: exception.message ]
    }

    @ResponseBody
    @RequestMapping(produces = Version.V1)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException)
    def handle(ConstraintViolationException exception) {
        [   error: 'validation failure',
            violations: exception.constraintViolations.collect {[
                    message: it.message
            ]}]
    }

    @ResponseBody
    @RequestMapping(produces = Version.V1)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException)
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
    @ExceptionHandler(ObjectRetrievalFailureException)
    def handle(ObjectRetrievalFailureException exception) {
        [ error: 'entity not found', cause: exception.message ]
    }

    @ResponseBody
    @RequestMapping(produces = Version.V1)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException)
    def handle(DataIntegrityViolationException exception) {
        String constraintName = exception.cause?.constraintName

        if (constraintName?.startsWith('unique_')) return [
                error: 'unique constraint violation',
                violations: [[
                    field: constraintName.replace('unique_', ''),
                    message: 'is already taken'
                ]]]

        else return [
                error: 'data integrity violation',
                cause: exception.cause?.cause?.localizedMessage ]
    }

    @ResponseBody
    @RequestMapping(produces = Version.V1)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EntityPropertyConstraintException)
    def handle(EntityPropertyConstraintException exception) {
        [   error: 'constraint violation',
            violations: [[
                field: exception.propertyName,
                message: exception.message ]]]
    }

    @ResponseBody
    @RequestMapping(produces = Version.V1)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataAccessException)
    def handle(DataAccessException exception) {
        [ error: 'data error', cause: exception.cause.message ]
    }

    @ResponseBody
    @RequestMapping(produces = Version.V1)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException)
    def handle(HttpMediaTypeNotSupportedException exception) {
        [   error: 'unsupported media type',
            cause: exception.localizedMessage,
            supported: exception.supportedMediaTypes.collect { (String) "${it.type}/${it.subtype}" }]
    }

    @ResponseBody
    @RequestMapping(produces = Version.V1)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotAcceptableException)
    def handle(HttpMediaTypeNotAcceptableException exception) {
        [   error: 'unsupported media type',
            cause: exception.message,
            supported: exception.supportedMediaTypes.collect { (String) "${it.type}/${it.subtype}" }]
    }

    @ResponseBody
    @RequestMapping(produces = Version.V1)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception)
    def handleUncaughtException(Exception exception) {
        [ error: 'unknown error', cause: exception.message ]
    }

    @ResponseBody
    @RequestMapping(produces = Version.V1)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException)
    def handleAccessDenied(AccessDeniedException exception) {
        if (SecurityContextHolder.context.authentication.authorities.any { it.authority == 'ROLE_ANONYMOUS' })
            new ResponseEntity(HttpStatus.UNAUTHORIZED)
        else
            [ error: 'access denied' ]
    }

    @ResponseBody
    @RequestMapping(produces = Version.V1)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchEntityException)
    def handle(NoSuchEntityException exception) {
        [ error: 'no such entity' ]
    }

}
