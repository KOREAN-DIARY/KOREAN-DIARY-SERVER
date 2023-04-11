package com.finalproject.kdiary.controller.pronunciation;

import com.finalproject.kdiary.service.PronunciationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/pronunciation")
public class PronunciationController {
    private final PronunciationService pronunciationService;

    @GetMapping()
    public String test() {

        return pronunciationService.test();
    }
}