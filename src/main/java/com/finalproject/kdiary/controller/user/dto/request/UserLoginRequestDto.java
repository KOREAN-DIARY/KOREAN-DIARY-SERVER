package com.finalproject.kdiary.controller.user.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class UserLoginRequestDto {
    private final String idTokenString;
}
