package com.finalproject.kdiary.exception.model;

import com.finalproject.kdiary.exception.ErrorStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException {
    private final ErrorStatus errorCode;
}
