package com.finalproject.kdiary.service;

import com.finalproject.kdiary.controller.diary.dto.request.DiaryCreateRequestDto;
import com.finalproject.kdiary.controller.diary.dto.response.DiaryCreateResponseDto;
import com.finalproject.kdiary.controller.diary.dto.response.DiaryReadResponseDto;
import com.finalproject.kdiary.domain.Diary;
import com.finalproject.kdiary.infrastructure.DiaryRepository;
import com.finalproject.kdiary.support.error.ErrorStatus;
import com.finalproject.kdiary.support.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryRepository diaryRepository;

    @Transactional
    public DiaryCreateResponseDto create(String userId, DiaryCreateRequestDto request) {
        Diary diary = Diary.builder()
                .content(request.getContent())
                .date(request.getDate())
                .speaking(request.getSpeaking())
                .writing(request.getWriting())
                .userId(userId)
                .build();

        try {
            diaryRepository.save(diary);
        } catch (DataIntegrityViolationException e) {
            throw new CustomException(ErrorStatus.UNAUTHORIZED);
        }
        return DiaryCreateResponseDto.of(diary.getId(), diary.getContent(), diary.getDate(), diary.getWriting(), diary.getSpeaking());

    }

    @Transactional
    public List<DiaryReadResponseDto> search(String userId) {
        List<Diary> diaryList = diaryRepository.findByUserId(userId);
        List<DiaryReadResponseDto> responseList = new ArrayList<>();
        for (Diary diary : diaryList) {
            responseList.add(
                    DiaryReadResponseDto.from(diary.getId(), diary.getContent(), diary.getDate(), diary.getWriting(), diary.getSpeaking()));
        }

        return responseList;
    }
}
