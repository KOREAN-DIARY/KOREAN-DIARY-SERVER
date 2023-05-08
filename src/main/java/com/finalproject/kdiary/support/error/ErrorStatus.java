package com.finalproject.kdiary.support.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorStatus {
    UNKNOWN_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 에러"),
    FAIL_TO_CREATE(HttpStatus.INTERNAL_SERVER_ERROR, "생성 실패"),
    NOT_LOGIN(HttpStatus.UNAUTHORIZED, "비 로그인 사용자"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "권한이 없습니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "사용자의 입력이 잘못되었습니다."),
    NOT_PERMITTED(HttpStatus.FORBIDDEN, "권한이 없는 유저 입니다."),
    DUPLICATE(HttpStatus.BAD_REQUEST, "중복 입력입니다."),
    ENTITY_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "엔터티를 찾을 수 없습니다."),
    UNEXPECTED_VALUE(HttpStatus.INTERNAL_SERVER_ERROR, "예상하지 못한 값입니다."),
    DELETD_USER(HttpStatus.FORBIDDEN, "삭제된 유저입니다."),
    AWS_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "AWS 에러입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
