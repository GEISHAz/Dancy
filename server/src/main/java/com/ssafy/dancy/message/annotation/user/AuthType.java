package com.ssafy.dancy.message.annotation.user;

import com.ssafy.dancy.message.validator.user.AuthTypeValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AuthTypeValidator.class)
public @interface AuthType {

    String message() default "KAKAO, NAVER, GOOGLE, DANCY 를 제외한 인증 타입은 존재하지 않습니다.";
    Class[] groups() default {};

    Class[] payload() default {};
}
