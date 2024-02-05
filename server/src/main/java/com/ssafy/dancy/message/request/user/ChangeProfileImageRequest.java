package com.ssafy.dancy.message.request.user;

import com.ssafy.dancy.message.annotation.file.ImageFile;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

public record ChangeProfileImageRequest(
        @NotNull(message = "프로필 사진 변경에서의 사진은 반드시 포함되어 있어야 합니다.")
        @ImageFile
        MultipartFile profileImage
) {
    @Builder
    public ChangeProfileImageRequest{

    }
}
