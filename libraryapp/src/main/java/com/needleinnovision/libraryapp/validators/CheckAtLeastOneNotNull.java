package com.needleinnovision.libraryapp.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = CheckAtLeastOneNotNullValidator.class)
@Documented
public @interface CheckAtLeastOneNotNull {
	String message() default "{com.xxx.constraints.checkatleastnotnull}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
       
    String[] fieldNames();
}
