package com.home.kit.constraint;

public interface ConstraintValidator {
    boolean isValid(Object o);

    default String getMessage() {
        return null;
    }
}
