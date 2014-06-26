package de.comprot.facade.v1.validation

import org.hibernate.validator.constraints.Length

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
@Pattern(regexp = '[\\w]+')
@Length(min = 3, max = 100)
@interface Username {

    String message() default '{error.validation.username}'

    Class<?>[] groups() default []

    Class<? extends Payload>[] payload() default []

}