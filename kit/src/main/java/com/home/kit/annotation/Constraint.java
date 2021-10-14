package com.home.kit.annotation;

import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@javax.validation.Constraint(validatedBy = ConstraintValidator.class)
@Documented
public @interface Constraint {
    String message() default "{constraint.value.violation}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    Class<? extends com.home.kit.constraint.ConstraintValidator>[] value() default {};
    boolean skipOnEmpty() default false;
}
