package com.finalproject.kdiary.exception;

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
    DELETED_USER(HttpStatus.FORBIDDEN, "삭제된 유저입니다."),
    AWS_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "AWS 에러입니다."),
    /**
     * speaking
     */
    FAIL_TO_CRAWL_SPEAKING_PAGE(HttpStatus.INTERNAL_SERVER_ERROR, "발음 평가 페이지 크롤링 실패"),
    FAIL_TO_PARSE_SPEAKING_JSON(HttpStatus.INTERNAL_SERVER_ERROR, "발음 평가 페이지 결과 JSON parsing 실패"),
    INVALID_AUDIO_FILE(HttpStatus.BAD_REQUEST, "잘못된 형식의 오디오 파일입니다"),
    /**
     * diary
     */
    NON_EXIST_DIARY(HttpStatus.NOT_FOUND, "존재하지 않는 일기입니다."),
    /**
     * user
     */
    NOT_FOUND_USER_EXCEPTION(HttpStatus.NOT_FOUND, "존재하지 않는 유저입니다."),
    INVALID_REFRESH_TOKEN_EXCEPTION(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다."),
    TOKEN_TIME_EXPIRED_EXCEPTION(HttpStatus.UNAUTHORIZED, "만료된 토큰입니다"),
    INVALID_GOOGLE_TOKEN_EXCEPTION(HttpStatus.UNAUTHORIZED, "유효하지 않은 구글 토큰입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
