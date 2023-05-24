package com.finalproject.kdiary.controller.user;

import com.finalproject.kdiary.controller.user.dto.request.UserCreateRequestDto;
import com.finalproject.kdiary.controller.user.dto.response.UserCreateResponseDto;
import com.finalproject.kdiary.exception.SuccessStatus;
import com.finalproject.kdiary.service.UserService;
import com.finalproject.kdiary.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/user")
    public ApiResponse<UserCreateResponseDto> create(@RequestBody @Valid final UserCreateRequestDto request) {
        return ApiResponse.success(SuccessStatus.CREATE_USER_SUCCESS, userService.create(request));

    }
}
