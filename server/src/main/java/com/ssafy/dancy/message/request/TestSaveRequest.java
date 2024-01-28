package com.ssafy.dancy.message.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

public record TestSaveRequest(
        @NotBlank(message = "이름을 입력해주세요")
        @Size(min = 1, max = 30, message = "이름은 1자 이상 30자 이하로 입력해 주세요")
        String name,
        @NotBlank(message = "주소를 입력해주세요")
        @Size(min = 1, max = 100, message = "주소는 100자 이하로 입력해주세요")
        String address) {

    @Builder
    public TestSaveRequest{

    }
}
