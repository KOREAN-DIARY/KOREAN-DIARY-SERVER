package com.finalproject.kdiary.controller.user.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
@Getter
@RequiredArgsConstructor
public class UserDetailResponseDto {
    private final String id;
    private final String email;
    private final String name;
    private final String picture;

    public static UserDetailResponseDto of(String id, String email, String name, String picture) {
        return new UserDetailResponseDto(id, email, name, picture);
    }
}
