package com.finalproject.kdiary.exception.model;

import com.finalproject.kdiary.exception.ErrorStatus;

public class UnauthorizedException extends CustomException {
    public UnauthorizedException(ErrorStatus error) {
        super(error);
    }
}