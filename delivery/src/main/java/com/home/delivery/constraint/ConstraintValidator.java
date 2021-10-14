package com.home.delivery.constraint;

public interface ConstraintValidator {
    boolean isValid(Object o);

    default String getMessage() {
        return null;
    }
}
