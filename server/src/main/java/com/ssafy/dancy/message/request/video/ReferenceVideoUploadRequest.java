package com.ssafy.dancy.message.request.video;

import com.ssafy.dancy.message.annotation.file.Video;
import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

@Builder
public record ReferenceVideoUploadRequest(
        @Video
        MultipartFile videoFile
) {
}
