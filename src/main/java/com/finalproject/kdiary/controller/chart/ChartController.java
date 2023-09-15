package com.finalproject.kdiary.controller.chart;

import com.finalproject.kdiary.common.dto.ApiResponse;
import com.finalproject.kdiary.config.resolver.UserId;
import com.finalproject.kdiary.controller.chart.dto.ChartDto;
import com.finalproject.kdiary.exception.SuccessStatus;
import com.finalproject.kdiary.service.ChartService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ChartController {

    private final ChartService chartService;

    @GetMapping("/chart")
    public ApiResponse<List<ChartDto>> getScore(@UserId String userId, @PageableDefault(size = 7) Pageable pageable) {
        return ApiResponse.success(SuccessStatus.GET_USER_SCORE, chartService.getUserScore(userId, pageable));
    }
}
