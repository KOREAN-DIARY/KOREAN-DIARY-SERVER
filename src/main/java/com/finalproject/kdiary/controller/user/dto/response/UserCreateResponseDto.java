package com.finalproject.kdiary.controller.user.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
@Getter
@RequiredArgsConstructor
public class UserCreateResponseDto {
    private final String id;
    private final String email;
    private final String name;

    public static UserCreateResponseDto of(String id, String email, String name) {
        return new UserCreateResponseDto(id, email, name);
    }
}
