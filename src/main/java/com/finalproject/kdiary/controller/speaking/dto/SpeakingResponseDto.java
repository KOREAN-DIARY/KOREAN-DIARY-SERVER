package com.finalproject.kdiary.controller.speaking.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
@Getter
@RequiredArgsConstructor
public class SpeakingResponseDto {
    private final String recognized;
    private final Double score;

    public static SpeakingResponseDto of(String recognized, Double score) {
        return new SpeakingResponseDto(recognized, score);
    }
}
