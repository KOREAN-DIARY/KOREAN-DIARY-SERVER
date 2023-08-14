package com.finalproject.kdiary.controller.user;

import com.finalproject.kdiary.controller.user.dto.request.TokenRefreshRequestDto;
import com.finalproject.kdiary.controller.user.dto.request.UserLoginRequestDto;
import com.finalproject.kdiary.controller.user.dto.response.TokenRefreshResponseDto;
import com.finalproject.kdiary.controller.user.dto.response.UserLoginResponseDto;
import com.finalproject.kdiary.exception.SuccessStatus;
import com.finalproject.kdiary.service.UserService;
import com.finalproject.kdiary.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<UserLoginResponseDto> login(@RequestBody @Valid final UserLoginRequestDto request) throws GeneralSecurityException, IOException {
        final String userId = userService.login(request);
        final String refreshToken = userService.generateRefreshToken(userId);
        final String accessToken = userService.generateAccessToken(refreshToken);
        return ApiResponse.success(SuccessStatus.LOGIN_SUCCESS, UserLoginResponseDto.of(accessToken, refreshToken));
    }

    @PostMapping("/token/refresh")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<TokenRefreshResponseDto> refreshToken(@RequestBody @Valid final TokenRefreshRequestDto request) {
        final String accessToken = userService.generateAccessToken(request.getRefreshToken());
        return ApiResponse.success(SuccessStatus.TOKEN_REFRESH_SUCCESS, TokenRefreshResponseDto.of(accessToken));
    }
}
