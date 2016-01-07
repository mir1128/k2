package org.k2.validation;

import org.k2.model.MoveDirection;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CorrectDirection implements ConstraintValidator<DirectionValidation, String>{
    @Override
    public void initialize(DirectionValidation constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            Enum.valueOf(MoveDirection.class, value.substring(0, 1).toUpperCase() + value.substring(1).toLowerCase());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
