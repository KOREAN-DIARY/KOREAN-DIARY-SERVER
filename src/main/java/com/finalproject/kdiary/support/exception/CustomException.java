package com.finalproject.kdiary.support.exception;

import com.finalproject.kdiary.support.error.ErrorStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException {
    private final ErrorStatus errorCode;
}
