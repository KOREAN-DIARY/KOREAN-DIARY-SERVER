package com.finalproject.kdiary.controller.diary;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/diary")
@RequiredArgsConstructor
public class DiaryController {
    
    @PostMapping()
    public void create() {

    }
}
