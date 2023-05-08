package com.finalproject.kdiary.controller.diary;

import com.finalproject.kdiary.controller.diary.dto.request.CreateRequestDto;
import com.finalproject.kdiary.controller.diary.dto.response.CreateResponseDto;
import com.finalproject.kdiary.service.DiaryService;
import com.finalproject.kdiary.support.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/diary")
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    @PostMapping()
    public ApiResponse<CreateResponseDto> create(CreateRequestDto request) {
        return ApiResponse.success(diaryService.create(request));
    }
}
