package com.finalproject.kdiary.support.exception;

import com.finalproject.kdiary.support.error.ErrorStatus;
import com.finalproject.kdiary.support.exception.CustomException;
import com.finalproject.kdiary.support.response.Response;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class ControllerExceptionHandler {

    @ExceptionHandler(CustomException.class)
    protected Response handleCustomException(final CustomException error) {
        return Response.error(error.getErrorCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected Response handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        return Response.error(ErrorStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    protected Response handleRuntimeException(final RuntimeException e) {
        return Response.error(ErrorStatus.UNKNOWN_ERROR);
    }

}
