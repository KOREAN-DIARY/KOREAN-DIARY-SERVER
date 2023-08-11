package com.finalproject.kdiary.exception.model;

import com.finalproject.kdiary.exception.ErrorStatus;

public class NotFoundException extends CustomException {
    public NotFoundException(ErrorStatus error) {
        super(error);
    }
}
