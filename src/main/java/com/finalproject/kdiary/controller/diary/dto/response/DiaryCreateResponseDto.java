package com.finalproject.kdiary.controller.diary.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@Getter
@RequiredArgsConstructor
public class DiaryCreateResponseDto {
    private final Long id;
    private final String content;
    private final Date date;
    private final int writing;
    private final int speaking;

    public static DiaryCreateResponseDto of(Long id, String content, Date date, int writing, int speaking) {
        return new DiaryCreateResponseDto(id, content, date, writing, speaking);
    }
}
