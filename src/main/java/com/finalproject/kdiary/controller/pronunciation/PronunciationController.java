package com.finalproject.kdiary.controller.pronunciation;

import com.finalproject.kdiary.controller.pronunciation.dto.PronunciationResponseDto;
import com.finalproject.kdiary.controller.pronunciation.dto.SpeakingScoreRequestDto;
import com.finalproject.kdiary.exception.SuccessStatus;
import com.finalproject.kdiary.service.PronunciationService;
import com.finalproject.kdiary.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/pronunciation")
public class PronunciationController {
    private final PronunciationService pronunciationService;
    
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<PronunciationResponseDto> createSpeakingScore(@ModelAttribute SpeakingScoreRequestDto request) {
        return ApiResponse.success(SuccessStatus.CREATE_SPEAKING_SCORE, pronunciationService.createSpeakingScore(request));
    }
}