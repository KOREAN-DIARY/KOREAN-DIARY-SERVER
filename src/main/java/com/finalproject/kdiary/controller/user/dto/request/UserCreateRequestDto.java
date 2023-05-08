package com.finalproject.kdiary.controller.user.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@RequiredArgsConstructor
public class UserCreateRequestDto {
    @Email(message = "이메일 형식에 맞지 않습니다")
    @NotNull
    private final String email;

    @NotBlank
    @Pattern(regexp = "^[가-힣a-zA-Z]{2,10}$", message = "이름을 입력해주세요.")
    private final String name;

}
