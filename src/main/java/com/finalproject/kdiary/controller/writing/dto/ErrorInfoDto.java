package com.finalproject.kdiary.controller.writing.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ErrorInfoDto {
    private final String help;
    private final String originalString;
    private final String correctWord;
}
