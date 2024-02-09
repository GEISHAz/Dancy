package com.ssafy.dancy.message.request.video;

import com.ssafy.dancy.message.annotation.file.Video;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record PracticeVideoUploadRequest (
        @NotNull(message = "연습 영상 비디오 아이디는 필수값입니다.")
        @Min(value = 0, message = "1 이상의 레퍼런스 비디오 ID 를 입력해 주세요")
        Long referenceVideoId,

        @Video
        MultipartFile videoFile
) {
}