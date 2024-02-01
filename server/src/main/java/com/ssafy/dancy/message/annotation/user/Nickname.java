package com.ssafy.dancy.message.annotation.user;

import com.ssafy.dancy.message.validator.user.NicknameValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({FIELD, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NicknameValidator.class)
public @interface Nickname {

    String message() default "닉네임은 1자 이상 15자 미만으로 입력해 주세요.";
    Class[] groups() default {};

    Class[] payload() default {};
}
