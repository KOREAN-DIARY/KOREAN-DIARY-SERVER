package com.finalproject.kdiary.controller.speaking;

import com.finalproject.kdiary.controller.speaking.dto.SpeakingResponseDto;
import com.finalproject.kdiary.controller.speaking.dto.SpeakingRequestDto;
import com.finalproject.kdiary.exception.SuccessStatus;
import com.finalproject.kdiary.service.SpeakingService;
import com.finalproject.kdiary.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/speaking")
@CrossOrigin(origins = "*")
public class SpeakingController {
    private final SpeakingService speakingServiceService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<SpeakingResponseDto> createSpeakingScore(@ModelAttribute SpeakingRequestDto request) {
        return ApiResponse.success(SuccessStatus.CREATE_SPEAKING_SCORE, speakingServiceService.createSpeakingScore(request));
    }
}