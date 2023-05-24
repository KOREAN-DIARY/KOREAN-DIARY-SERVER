package com.finalproject.kdiary.controller.pronunciation.dto;

import com.finalproject.kdiary.controller.user.dto.response.UserCreateResponseDto;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
@Getter
@RequiredArgsConstructor
public class PronunciationResponseDto {
    private final String recognized;
    private final Double score;

    public static PronunciationResponseDto of(String recognized, Double score) {
        return new PronunciationResponseDto(recognized, score);
    }
}
