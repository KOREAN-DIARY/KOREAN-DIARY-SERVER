package com.finalproject.kdiary.service;

import com.finalproject.kdiary.controller.chart.dto.ChartDto;
import com.finalproject.kdiary.controller.chart.dto.ChartScoreDto;
import com.finalproject.kdiary.domain.Diary;
import com.finalproject.kdiary.infrastructure.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChartService {

    private final DiaryRepository diaryRepository;

    public List<ChartDto> getUserScore(String userId, Pageable pageable) {
        Page<Diary> diaryList = diaryRepository.findByUserIdOrderByDateDesc(userId, pageable);
        List<ChartScoreDto> speakingScores = new ArrayList<>();
        List<ChartScoreDto> writingScores = new ArrayList<>();
        List<ChartScoreDto> totalScores = new ArrayList<>();
        ListIterator li = diaryList.toList().listIterator(diaryList.getSize());

        /* score data */
        SimpleDateFormat format = new SimpleDateFormat("M/d");
        while (li.hasPrevious()) {
            Diary diary = (Diary) li.previous();
            String date = format.format(diary.getDate());
            speakingScores.add(ChartScoreDto.of(date, (double) diary.getSpeaking()));
            writingScores.add(ChartScoreDto.of(date, (double) diary.getWriting()));

            Double avg = (diary.getSpeaking() + diary.getWriting()) / 2.0;
            totalScores.add(ChartScoreDto.of(date, avg));
        }

        /* chart response data */
        List<ChartDto> chartList = new ArrayList<>();
        chartList.add(ChartDto.of("총점", "hsl(152, 70%, 50%)", totalScores));
        chartList.add(ChartDto.of("쓰기", "hsl(225, 70%, 50%)", writingScores));
        chartList.add(ChartDto.of("말하기", "hsl(102, 70%, 50%)", speakingScores));

        return chartList;
    }
}
