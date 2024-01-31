package com.ssafy.dancy.message.annotation.file;

import com.ssafy.dancy.message.validator.file.ImageFileValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ImageFileValidator.class)
public @interface ImageFile {
    String message() default "입력된 파일이 이미지가 아닙니다.";

    Class[] groups() default {};

    Class[] payload() default {};
}