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
import java.util.ArrayList;
import java.util.List;

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

        /* score data */
        SimpleDateFormat format = new SimpleDateFormat("M/d");
        for (Diary diary : diaryList) {
            System.out.println(diary.getSpeaking() + " " + diary.getWriting());
            String date = format.format(diary.getDate());
            speakingScores.add(ChartScoreDto.of(date, (double) diary.getSpeaking()));
            writingScores.add(ChartScoreDto.of(date, (double) diary.getWriting()));

            Double avg = (diary.getSpeaking() + diary.getWriting()) / 2.0;
            totalScores.add(ChartScoreDto.of(date, avg));
        }

        /* chart response data */
        List<ChartDto> chartList = new ArrayList<>();
        chartList.add(ChartDto.of("말하기", "hsl(102, 70%, 50%)", speakingScores));
        chartList.add(ChartDto.of("쓰기", "hsl(225, 70%, 50%)", writingScores));
        chartList.add(ChartDto.of("최종 점수", "hsl(152, 70%, 50%)", totalScores));

        return chartList;
    }
}
