package com.finalproject.kdiary.exception.model;

import com.finalproject.kdiary.exception.ErrorStatus;

public class InvalidGoogleTokenException extends CustomException {
    public InvalidGoogleTokenException(ErrorStatus error) {
        super(error);
    }
}
