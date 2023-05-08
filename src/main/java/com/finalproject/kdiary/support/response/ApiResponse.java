package com.finalproject.kdiary.support.response;

import com.finalproject.kdiary.support.error.ErrorStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class ApiResponse<T> {
    private final T data;
    private final HttpStatus httpStatus;
    private final int code;
    private final String message;


    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<T>(data, HttpStatus.OK, 200, null);
    }

    public static <T> ApiResponse<T> success(T data, HttpStatus status) {
        return new ApiResponse<T>(data, status, status.value(), null);
    }

    public static <T> ApiResponse<T> success(HttpStatus status, String message) {
        return new ApiResponse<T>(null, status, status.value(), message);
    }

    public static ApiResponse error(ErrorStatus errorStatus) {
        return new ApiResponse(null, errorStatus.getHttpStatus(), errorStatus.getHttpStatus().value(), errorStatus.getMessage());
    }
}
