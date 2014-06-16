package de.comprot.core.model.validation

import javax.validation.Constraint
import javax.validation.Payload
import javax.validation.ReportAsSingleViolation
import javax.validation.constraints.Pattern
import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.Target

import static java.lang.annotation.ElementType.*
import static java.lang.annotation.RetentionPolicy.RUNTIME

@Documented
@Retention(RUNTIME)
@Target([ METHOD, FIELD, ANNOTATION_TYPE ])
@Constraint(validatedBy = [])
@ReportAsSingleViolation
@Pattern(regexp  = '^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}$')
@interface Password {

    String message() default '{error.validation.password}'

    Class<?>[] groups() default []

    Class<? extends Payload>[] payload() default []

}