package com.finalproject.kdiary.exception.model;


import com.finalproject.kdiary.exception.ErrorStatus;

public class InvalidRefreshTokenException extends CustomException {
    public InvalidRefreshTokenException(ErrorStatus error) {
        super(error);
    }
}