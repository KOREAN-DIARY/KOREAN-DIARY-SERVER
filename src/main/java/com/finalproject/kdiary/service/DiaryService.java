package com.finalproject.kdiary.service;

import com.finalproject.kdiary.controller.diary.dto.request.DiaryCreateRequestDto;
import com.finalproject.kdiary.controller.diary.dto.response.DiaryCreateResponseDto;
import com.finalproject.kdiary.controller.diary.dto.response.DiaryReadResponseDto;
import com.finalproject.kdiary.domain.Diary;
import com.finalproject.kdiary.infrastructure.DiaryRepository;
import com.finalproject.kdiary.exception.ErrorStatus;
import com.finalproject.kdiary.exception.model.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryRepository diaryRepository;

    @Transactional
    public DiaryCreateResponseDto create(String userId, DiaryCreateRequestDto request) {
        Diary diary = Diary.builder()
                .content(request.getContent())
                .originalContent(request.getOriginalContent())
                .date(request.getDate())
                .speaking(request.getSpeaking())
                .writing(request.getWriting())
                .userId(userId)
                .build();

        Optional<Diary> existDiary = diaryRepository.findByUserIdAndDate(userId, request.getDate());
        if (existDiary.isPresent()) {
            throw new CustomException(ErrorStatus.BAD_REQUEST);
        }

        try {
            diaryRepository.save(diary);
        } catch (DataIntegrityViolationException e) {
            throw new CustomException(ErrorStatus.UNAUTHORIZED);
        }
        return DiaryCreateResponseDto.of(diary.getId(), diary.getContent(), diary.getOriginalContent(), diary.getDate(), diary.getWriting(), diary.getSpeaking());

    }

    @Transactional
    public List<DiaryReadResponseDto> search(String userId) {
        List<Diary> diaryList = diaryRepository.findByUserId(userId);
        List<DiaryReadResponseDto> responseList = new ArrayList<>();
        for (Diary diary : diaryList) {
            responseList.add(
                    DiaryReadResponseDto.from(diary.getId(), diary.getContent(), diary.getOriginalContent(), diary.getDate(), diary.getWriting(), diary.getSpeaking()));
        }

        return responseList;
    }

    @Transactional
    public DiaryReadResponseDto getDetailByDate(String userId, Date date) {
        Optional<Diary> optional = diaryRepository.findByUserIdAndDate(userId, date);
        if (optional.isEmpty()) {
            throw new CustomException(ErrorStatus.NON_EXIST_DIARY);
        }

        Diary diary = optional.get();


        return DiaryReadResponseDto.from(diary.getId(), diary.getContent(), diary.getOriginalContent(), diary.getDate(), diary.getWriting(), diary.getSpeaking());
    }

    @Transactional
    public DiaryReadResponseDto getDetail(Long id) {
        Optional<Diary> optional = diaryRepository.findById(id);
        if (optional.isEmpty()) {
            throw new CustomException(ErrorStatus.NON_EXIST_DIARY);
        }

        Diary diary = optional.get();


        return DiaryReadResponseDto.from(diary.getId(), diary.getContent(), diary.getOriginalContent(), diary.getDate(), diary.getWriting(), diary.getSpeaking());
    }
}
