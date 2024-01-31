package com.ssafy.dancy.message.request.user;

import com.ssafy.dancy.message.annotation.file.ImageFile;
import com.ssafy.dancy.message.annotation.user.AuthType;
import com.ssafy.dancy.message.annotation.user.DateFormat;
import com.ssafy.dancy.message.annotation.user.Gender;
import com.ssafy.dancy.message.annotation.user.Password;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;


public record SignUpRequest(
        @Email
        String email,

        @NotNull(message = "닉네임을 입력해 주세요.")
        @Size(min = 1, max = 15, message = "닉네임은 1자 이상 15자 미만으로 입력해 주세요.")
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
