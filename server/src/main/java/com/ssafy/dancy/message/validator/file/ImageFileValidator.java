package com.ssafy.dancy.message.validator.file;

import com.ssafy.dancy.message.annotation.file.ImageFile;
import com.ssafy.dancy.type.ReferenceType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ImageFileValidator implements ConstraintValidator<ImageFile, MultipartFile> {

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        if(value == null){
            return true;
        }
        return ReferenceType.isValidFileType(ReferenceType.IMAGE, List.of(value));
    }
}