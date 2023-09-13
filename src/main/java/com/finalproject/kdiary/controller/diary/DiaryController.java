package com.finalproject.kdiary.controller.diary;

import com.finalproject.kdiary.config.jwt.JwtService;
import com.finalproject.kdiary.config.resolver.UserId;
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
@CrossOrigin(origins = "*")
public class DiaryController {

    private final DiaryService diaryService;
    private final JwtService jwtService;

    @PostMapping("/diary")
    public ApiResponse<DiaryCreateResponseDto> create(@UserId String userId, @RequestBody @Valid final DiaryCreateRequestDto request) {
        return ApiResponse.success(SuccessStatus.CREATE_DIARY_SUCCESS, diaryService.create(userId, request));
    }

    @GetMapping("/diary")
    public ApiResponse<List<DiaryReadResponseDto>> search(@UserId String userId) {
        return ApiResponse.success(SuccessStatus.GET_DIARY_LIST_SUCCESS, diaryService.search(userId));
    }

    @GetMapping("/diary/search")
    public ApiResponse<DiaryReadResponseDto> getDetailByDate(@UserId String userId, @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return ApiResponse.success(SuccessStatus.GET_DIARY_SUCCESS, diaryService.getDetailByDate(userId, date));
    }

    @GetMapping("/diary/{diaryId}")
    public ApiResponse<DiaryReadResponseDto> getDetail(@UserId String userId, @PathVariable final Long diaryId) {
        return ApiResponse.success(SuccessStatus.GET_DIARY_SUCCESS, diaryService.getDetail(diaryId));
    }


}
