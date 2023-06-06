package com.finalproject.kdiary.controller.diary;

import com.finalproject.kdiary.controller.diary.dto.request.DiaryCreateRequestDto;
import com.finalproject.kdiary.controller.diary.dto.response.DiaryCreateResponseDto;
import com.finalproject.kdiary.controller.diary.dto.response.DiaryReadResponseDto;
import com.finalproject.kdiary.exception.SuccessStatus;
import com.finalproject.kdiary.service.DiaryService;
import com.finalproject.kdiary.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    @PostMapping("/diary")
    public ApiResponse<DiaryCreateResponseDto> create(@RequestBody @Valid final DiaryCreateRequestDto request) {
        // TODO: replace user id with real jwt token
        String userId = "7190a79b-5d7e-43f2-b22b-46b2a8b9e6e5";
        return ApiResponse.success(SuccessStatus.CREATE_DIARY_SUCCESS, diaryService.create(userId, request));
    }

    @GetMapping("/diary")
    public ApiResponse<List<DiaryReadResponseDto>> search() {
        String userId = "7190a79b-5d7e-43f2-b22b-46b2a8b9e6e5";
        return ApiResponse.success(SuccessStatus.GET_DIARY_LIST_SUCCESS, diaryService.search(userId));
    }

    @GetMapping("/diary/search")
    public ApiResponse<DiaryReadResponseDto> getDetailByDate(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return ApiResponse.success(SuccessStatus.GET_DIARY_SUCCESS, diaryService.getDetailByDate(date));
    }

    @GetMapping("/diary/{diaryId}")
    public ApiResponse<DiaryReadResponseDto> getDetail(@PathVariable final Long diaryId) {
        return ApiResponse.success(SuccessStatus.GET_DIARY_SUCCESS, diaryService.getDetail(diaryId));
    }


}
