package com.finalproject.kdiary.controller.speaking;

import com.finalproject.kdiary.controller.speaking.dto.NewSpeakingRequestDto;
import com.finalproject.kdiary.controller.speaking.dto.SpeakingResponseDto;
import com.finalproject.kdiary.controller.speaking.dto.SpeakingRequestDto;
import com.finalproject.kdiary.controller.user.dto.request.TokenRefreshRequestDto;
import com.finalproject.kdiary.exception.SuccessStatus;
import com.finalproject.kdiary.service.SpeakingService;
import com.finalproject.kdiary.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/speaking")
@CrossOrigin(origins = "*")
public class SpeakingController {
    private final SpeakingService speakingServiceService;

    @PostMapping()
    public ApiResponse<SpeakingResponseDto> createSpeakingScore(@RequestBody @Valid final NewSpeakingRequestDto request) {
        return ApiResponse.success(SuccessStatus.CREATE_SPEAKING_SCORE, speakingServiceService.createNewSpeakingScore(request));
    }
}