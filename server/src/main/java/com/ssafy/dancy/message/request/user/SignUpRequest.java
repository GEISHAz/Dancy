package com.ssafy.dancy.message.request.user;

import com.ssafy.dancy.message.annotation.file.ImageFile;
import com.ssafy.dancy.message.annotation.user.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;


public record SignUpRequest(
        @NotNull(message = "이메일을 입력해 주세요.")
        @Email(message = "이메일 형식으로 입력해 주세요.")
        String email,

        @NotNull(message = "닉네임을 입력해 주세요.")
        @Nickname
        String nickname,

        @NotNull(message = "비밀번호를 입력해 주세요")
        @Password
        String password,
        @NotNull(message = "성별을 입력해 주세요")
        @Gender
        String gender,

        @NotNull(message = "생년월일을 입력해주세요")
        @DateFormat
        String birthDate,

        @NotNull(message = "인증 타입을 입력해 주세요.")
        @AuthType
        String authType,

        @ImageFile
        MultipartFile profileImage
) {

        @Builder
        public SignUpRequest{

        }
}
