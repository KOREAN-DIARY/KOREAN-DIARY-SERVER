package com.finalproject.kdiary.common.advice;

import com.finalproject.kdiary.common.dto.ApiResponse;
import com.finalproject.kdiary.exception.model.CustomException;
import com.finalproject.kdiary.exception.ErrorStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class ControllerExceptionAdvice {

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ApiResponse> handleCustomException(final CustomException error) {
        return ResponseEntity.status(error.getErrorCode().getHttpStatus()).body(ApiResponse.error(error.getErrorCode()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(ErrorStatus.BAD_REQUEST));
    }

//    @ExceptionHandler(RuntimeException.class)
//    protected Response handleRuntimeException(final RuntimeException e) {
//        return Response.error(ErrorStatus.UNKNOWN_ERROR);
//    }

}
