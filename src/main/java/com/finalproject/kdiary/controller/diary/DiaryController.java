package com.finalproject.kdiary.controller.diary;

import com.finalproject.kdiary.controller.diary.dto.request.CreateRequestDto;
import com.finalproject.kdiary.controller.diary.dto.response.CreateResponseDto;
import com.finalproject.kdiary.service.DiaryService;
import com.finalproject.kdiary.support.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    @PostMapping("/diary")
    public ApiResponse<CreateResponseDto> create(@RequestBody @Valid final CreateRequestDto request) {
        return ApiResponse.success(diaryService.create(request));
    }
}
