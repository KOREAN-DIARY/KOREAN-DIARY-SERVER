package com.finalproject.kdiary.controller.diary;

import com.finalproject.kdiary.controller.diary.dto.request.DiaryCreateRequestDto;
import com.finalproject.kdiary.controller.diary.dto.response.DiaryCreateResponseDto;
import com.finalproject.kdiary.controller.diary.dto.response.DiaryReadResponseDto;
import com.finalproject.kdiary.service.DiaryService;
import com.finalproject.kdiary.support.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    @PostMapping("/diary")
    public Response<DiaryCreateResponseDto> create(@RequestBody @Valid final DiaryCreateRequestDto request) {
        // TODO: replace user id with real jwt token
        String userId = "91b6bc99-45f2-4bad-a73c-ba8c8a4010f8";
        return Response.success(diaryService.create(userId, request), HttpStatus.CREATED);
    }

    @GetMapping("/diary")
    public Response<List<DiaryReadResponseDto>> search(@RequestParam final String user_id) {
        return Response.success(diaryService.search(user_id));
    }
}
