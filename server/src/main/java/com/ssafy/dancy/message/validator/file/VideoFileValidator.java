package com.ssafy.dancy.message.validator.file;

import com.ssafy.dancy.message.annotation.file.Video;
import com.ssafy.dancy.type.ReferenceType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class VideoFileValidator implements ConstraintValidator<Video, MultipartFile> {
    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null){
            return false;
        }

        boolean hasBarOrUnderBar =
                value.getOriginalFilename() != null && !value.getOriginalFilename().matches(".*[-_].*");

        return hasBarOrUnderBar && ReferenceType.isValidFileType(ReferenceType.VIDEO, List.of(value));
    }
}
