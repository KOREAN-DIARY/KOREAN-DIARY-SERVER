package com.finalproject.kdiary.controller.diary;

import com.finalproject.kdiary.controller.diary.dto.request.DiaryCreateRequestDto;
import com.finalproject.kdiary.controller.diary.dto.response.DiaryCreateResponseDto;
import com.finalproject.kdiary.controller.diary.dto.response.DiaryReadResponseDto;
import com.finalproject.kdiary.service.DiaryService;
import com.finalproject.kdiary.support.response.Response;
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
    public Response<DiaryCreateResponseDto> create(@RequestBody @Valid final DiaryCreateRequestDto request) {
        // TODO: replace user id with real jwt token
        String userId = "f127cb54-5c01-4235-9f4c-2ef672786fa7";
        return Response.success(diaryService.create(userId, request), HttpStatus.CREATED);
    }

    @GetMapping("/diary")
    public Response<List<DiaryReadResponseDto>> search() {
        String userId = "f127cb54-5c01-4235-9f4c-2ef672786fa7";
        return Response.success(diaryService.search(userId));
    }

    @GetMapping("/diary/search")
    public Response<DiaryReadResponseDto> getDetailByDate(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return Response.success(diaryService.getDetailByDate(date));
    }


}
