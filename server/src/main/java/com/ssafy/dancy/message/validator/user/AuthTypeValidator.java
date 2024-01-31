package com.ssafy.dancy.message.validator.user;

import com.ssafy.dancy.message.annotation.user.AuthType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AuthTypeValidator implements ConstraintValidator<AuthType, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            com.ssafy.dancy.type.AuthType.valueOf(value);
        } catch (IllegalArgumentException ex) {
            return false;
        }

        return true;
    }
}