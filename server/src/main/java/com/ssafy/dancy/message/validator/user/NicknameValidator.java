package com.ssafy.dancy.message.validator.user;

import com.ssafy.dancy.message.annotation.user.Nickname;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NicknameValidator implements ConstraintValidator<Nickname, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        int length = value.length();
        return 1 <= length && length < 15;
    }
}