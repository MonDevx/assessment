package com.kbtg.bootcamp.posttest.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserIdValidator implements ConstraintValidator<ValidUserId, Integer> {
    @Override
    public void initialize(ValidUserId constraintAnnotation) {
    }

    public UserIdValidator() {
    }

    @Override
    public boolean isValid(Integer userId, ConstraintValidatorContext constraintValidatorContext) {
        return userId.toString().length() == 10;
    }

    public Boolean isValid(Integer userId) {
        return userId.toString().length() == 10;
    }
}
