package com.finalproject.kdiary.controller.diary.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
public class CreateRequestDto {
    private final String content;
    private final Date date;
    private final int writingScore;
    private final int speakingScore;
}
