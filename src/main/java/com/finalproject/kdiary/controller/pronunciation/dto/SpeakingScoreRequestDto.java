package com.finalproject.kdiary.controller.pronunciation.dto;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class SpeakingScoreRequestDto {
    @NotNull
    private MultipartFile audio;

    @NotBlank
    private String script;
}
