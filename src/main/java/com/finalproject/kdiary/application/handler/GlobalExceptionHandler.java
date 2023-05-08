package com.finalproject.kdiary.application.handler;

import com.finalproject.kdiary.support.error.ErrorStatus;
import com.finalproject.kdiary.support.exception.CustomException;
import com.finalproject.kdiary.support.response.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    protected ApiResponse handleCustomException(final CustomException error) {
        return ApiResponse.error(error.getErrorCode());
    }
//    protected ApiResponseDto{
//        val error: CoopaError = e.error
//        logger.error { "CoopaException : ${error.desc}" }
//        logger.error { "${error.desc} : ${e.printStackTrace()}" }
//        return ResponseEntity
//                .status(error.status)
//                .body(CoopaResponse(error.status, error.desc))
//    }
//
//    @ExceptionHandler(MultipartException::class)
//    fun multipartException(e: MultipartException): ResponseEntity<CoopaResponse<Unit>> {
//        logger.error { "MultipartException : ${e.localizedMessage}" }
//        return ResponseEntity
//                .status(400)
//                .body(CoopaResponse(HttpStatus.BAD_REQUEST, "이미지가 입력되지 않았습니다."))
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException::class)
//    fun methodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<CoopaResponse<Unit>> {
//        logger.error { "MethodArgumentNotValidException : " + e.message };
//        return ResponseEntity
//                .status(HttpStatus.BAD_REQUEST)
//                .body(CoopaResponse(HttpStatus.BAD_REQUEST, e.message));
//    }
//
//    @ExceptionHandler(RuntimeException::class)
//    fun unknownException(e: Exception): ResponseEntity<CoopaResponse<Unit>> {
//        logger.error { "UnknownException : ${e.localizedMessage} : ${e.stackTraceToString()}" }
//        return ResponseEntity
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(CoopaResponse(HttpStatus.INTERNAL_SERVER_ERROR, CoopaError.UNKNOWN_ERROR.desc))
//    }
}
