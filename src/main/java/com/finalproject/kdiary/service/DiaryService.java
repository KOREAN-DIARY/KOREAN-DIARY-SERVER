package com.finalproject.kdiary.service;

import com.finalproject.kdiary.controller.diary.dto.request.CreateRequestDto;
import com.finalproject.kdiary.controller.diary.dto.response.CreateResponseDto;
import com.finalproject.kdiary.domain.Diary;
import com.finalproject.kdiary.infrastructure.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryRepository diaryRepository;

    @Transactional
    public CreateResponseDto create(CreateRequestDto request) {
        Diary diary = Diary.builder()
                .content(request.getContent())
                .date(request.getDate())
                .speaking(request.getSpeaking())
                .writing(request.getWriting())
                .build();

        diaryRepository.save(diary);
        return CreateResponseDto.of(diary.getId(), diary.getContent(), diary.getDate(), diary.getWriting(), diary.getSpeaking());

    }
}
