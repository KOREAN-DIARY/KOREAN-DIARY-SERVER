package com.finalproject.kdiary.controller.chart.dto;

import lombok.Data;

import java.util.List;

@Data
public class ChartDto {
    private final String id;                  // label
    private final String color;
    private final List<ChartScoreDto> data;   // scores

    public static ChartDto of(String id, String color, List<ChartScoreDto> data) {
        return new ChartDto(id, color, data);
    }
}
