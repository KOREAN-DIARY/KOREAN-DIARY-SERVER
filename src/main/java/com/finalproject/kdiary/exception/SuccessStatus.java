package com.finalproject.kdiary.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessStatus {

    /**
     * 발음 api
     */
    CREATE_SPEAKING_SCORE(HttpStatus.CREATED, "발음 점수 생성 성공"),
    /**
     * diary api
     */
    CREATE_DIARY_SUCCESS(HttpStatus.CREATED, "다이어리 생성 성공"),
    GET_DIARY_SUCCESS(HttpStatus.OK, "다이어리 불러오기 성공"),
    GET_DIARY_LIST_SUCCESS(HttpStatus.OK, "다이어리 리스트 불러오기 성공"),

    /**
     * writing api
     */
    CREATE_WRITING_SCORE(HttpStatus.CREATED, "쓰기 점수 생성 성공"),
    /**
     * user api
     */
    LOGIN_SUCCESS(HttpStatus.OK, "로그인 성공"),
    TOKEN_REFRESH_SUCCESS(HttpStatus.OK, "토큰 리프레시 성공"),
    CREATE_USER_SUCCESS(HttpStatus.CREATED, "유저 생성 성공"),
    GET_USER_DETAIL(HttpStatus.OK, "유저 정보 조회 성공");

    private final HttpStatus httpStatus;
    private final String message;
}
