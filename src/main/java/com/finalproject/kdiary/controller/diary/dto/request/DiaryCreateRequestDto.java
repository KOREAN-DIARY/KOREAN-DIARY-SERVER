package com.finalproject.kdiary.controller.diary.dto.request;

import lombok.*;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@RequiredArgsConstructor
public class DiaryCreateRequestDto {
    @NotBlank
    private final String content;
    @NotBlank
    private final String originalContent;
    @NotNull
    private final Date date;
    @NotNull
    private final int writing;
    @NotNull
    private final int speaking;
}
