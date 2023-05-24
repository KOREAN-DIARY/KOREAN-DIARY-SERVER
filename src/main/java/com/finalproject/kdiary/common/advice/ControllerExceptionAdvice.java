package com.finalproject.kdiary.common.advice;

import com.finalproject.kdiary.common.dto.Response;
import com.finalproject.kdiary.exception.model.CustomException;
import com.finalproject.kdiary.exception.ErrorStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class ControllerExceptionAdvice {

    @ExceptionHandler(CustomException.class)
    protected Response handleCustomException(final CustomException error) {
        return Response.error(error.getErrorCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected Response handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        return Response.error(ErrorStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(RuntimeException.class)
//    protected Response handleRuntimeException(final RuntimeException e) {
//        return Response.error(ErrorStatus.UNKNOWN_ERROR);
//    }

}
