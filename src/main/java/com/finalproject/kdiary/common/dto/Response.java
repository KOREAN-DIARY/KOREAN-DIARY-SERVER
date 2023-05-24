package com.finalproject.kdiary.common.dto;

import com.finalproject.kdiary.exception.ErrorStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class Response<T> {
    private final T data;
    private final HttpStatus httpStatus;
    private final int code;
    private final String message;


    public static <T> Response<T> success(T data) {
        return new Response<T>(data, HttpStatus.OK, 200, null);
    }

    public static <T> Response<T> success(T data, HttpStatus status) {
        return new Response<T>(data, status, status.value(), null);
    }

    public static <T> Response<T> success(HttpStatus status, String message) {
        return new Response<T>(null, status, status.value(), message);
    }

    public static Response error(ErrorStatus errorStatus) {
        return new Response(null, errorStatus.getHttpStatus(), errorStatus.getHttpStatus().value(), errorStatus.getMessage());
    }
}
