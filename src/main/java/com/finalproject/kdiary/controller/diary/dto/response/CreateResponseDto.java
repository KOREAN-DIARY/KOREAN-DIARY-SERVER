package com.finalproject.kdiary.controller.diary.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
public class CreateResponseDto {
    private final Long id;
    private final String content;
    private final Date date;
    private final int writingScore;
    private final int speakingScore;

    public static CreateResponseDto of(Long id, String content, Date date, int writingScore, int speakingScore) {
        return new CreateResponseDto(id, content, date, writingScore, speakingScore);
    }
}
