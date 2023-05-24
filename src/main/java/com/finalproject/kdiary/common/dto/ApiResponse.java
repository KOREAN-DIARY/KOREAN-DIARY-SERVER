package com.finalproject.kdiary.common.dto;

import com.finalproject.kdiary.exception.ErrorStatus;
import com.finalproject.kdiary.exception.SuccessStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class ApiResponse<T> {
    private final T data;
    private final HttpStatus httpStatus;
    private final String message;


    public static <T> ApiResponse<T> success(SuccessStatus status) {

        return new ApiResponse<T>(null, status.getHttpStatus(), status.getMessage());
    }

    public static <T> ApiResponse<T> success(SuccessStatus status, T data) {
        return new ApiResponse<T>(data, status.getHttpStatus(), status.getMessage());
    }


    public static ApiResponse error(ErrorStatus errorStatus) {
        return new ApiResponse(null, errorStatus.getHttpStatus(), errorStatus.getMessage());
    }
}
