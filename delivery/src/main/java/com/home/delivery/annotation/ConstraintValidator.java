package com.home.delivery.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.validation.ConstraintValidatorContext;


public class ConstraintValidator implements javax.validation.ConstraintValidator<Constraint, Object> {

    @Autowired
    private ApplicationContext applicationContext;

    private com.home.delivery.constraint.ConstraintValidator[] constraintValidators;
    private boolean skipOnEmpty;

    @Override
    public void initialize(Constraint constraint) {
        Class<? extends com.home.delivery.constraint.ConstraintValidator>[] classes = constraint.value();
        constraintValidators = new com.home.delivery.constraint.ConstraintValidator[classes.length];
        this.skipOnEmpty = constraint.skipOnEmpty();
        for (int i = 0; i < classes.length; i++) {
            constraintValidators[i] = applicationContext.getBean(classes[i]);
        }
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext context) {


        if (o == null && skipOnEmpty) {
            return true;
        }
        for (com.home.delivery.constraint.ConstraintValidator constraintValidator : constraintValidators) {
            if (!constraintValidator.isValid(o)) {

                if (constraintValidator.getMessage() != null) {
                    context.disableDefaultConstraintViolation();
                    context.buildConstraintViolationWithTemplate(constraintValidator.getMessage())
                            .addConstraintViolation();
                }

                return false;
            }
        }
        return true;
    }
}
