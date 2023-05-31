package com.finalproject.kdiary.controller.writing.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Getter
@RequiredArgsConstructor
public class WritingResponseDto {
    private final String script;
    private final List<ErrorInfoDto> errorInfoList;

    public static WritingResponseDto of(String recognized, List<ErrorInfoDto> errorInfoList) {
        return new WritingResponseDto(recognized, errorInfoList);
    }
}
