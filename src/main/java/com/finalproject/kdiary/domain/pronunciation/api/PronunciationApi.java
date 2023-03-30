package com.finalproject.kdiary.domain.pronunciation.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/pronunciation")
public class PronunciationApi {
    @GetMapping()
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name){
        return String.format("Hello %s!",name);
    }
}