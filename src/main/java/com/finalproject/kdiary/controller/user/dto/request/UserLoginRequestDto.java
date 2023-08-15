package com.finalproject.kdiary.controller.user.dto.request;

import lombok.*;


@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class UserLoginRequestDto {
    private String credential;
}
