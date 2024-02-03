package com.ssafy.dancy.message.request.user;

import com.ssafy.dancy.message.annotation.file.ImageFile;
import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

public record ChangeProfileImageRequest(
        @ImageFile
        MultipartFile profileImage
) {
    @Builder
    public ChangeProfileImageRequest{

    }
}
