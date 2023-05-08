package com.finalproject.kdiary.controller.diary.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@RequiredArgsConstructor
public class CreateRequestDto {
    @NotBlank
    private final String content;
    @NotNull
    private final Date date;
    @NotNull
    private final int writing;
    @NotNull
    private final int speaking;
}
