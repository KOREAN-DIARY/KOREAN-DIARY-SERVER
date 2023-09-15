package com.finalproject.kdiary.controller.chart.dto;

import lombok.Data;
import org.checkerframework.checker.units.qual.C;

@Data
public class ChartScoreDto {
    private final String x;     // date
    private final Double y;     // score

    public static ChartScoreDto of(String date, Double score) {
        return new ChartScoreDto(date, score);
    }
}
