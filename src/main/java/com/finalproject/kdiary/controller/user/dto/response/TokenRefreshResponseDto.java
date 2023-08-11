package com.finalproject.kdiary.controller.user.dto.response;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenRefreshResponseDto {
    private String accessToken;

    public static TokenRefreshResponseDto of(String accessToken) {
        return new TokenRefreshResponseDto(accessToken);
    }
}