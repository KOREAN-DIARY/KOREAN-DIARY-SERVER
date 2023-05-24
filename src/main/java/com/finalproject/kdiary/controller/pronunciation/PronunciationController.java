package com.finalproject.kdiary.controller.pronunciation;

import com.finalproject.kdiary.controller.pronunciation.dto.PronunciationResponseDto;
import com.finalproject.kdiary.exception.SuccessStatus;
import com.finalproject.kdiary.service.PronunciationService;
import com.finalproject.kdiary.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/pronunciation")
public class PronunciationController {
    private final PronunciationService pronunciationService;

    @GetMapping()
    public ApiResponse<PronunciationResponseDto> test() {
        return ApiResponse.success(SuccessStatus.GET_PRONUNCIATION_SUCCESS, pronunciationService.getPronunciationScore());
    }
}