package com.finalproject.kdiary.domain.home.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class HomeApi {
    @GetMapping("/")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name){
        return String.format("Hello %s!",name);
    }
}
