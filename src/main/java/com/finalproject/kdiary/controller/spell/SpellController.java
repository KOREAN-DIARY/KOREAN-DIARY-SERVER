package com.finalproject.kdiary.controller.spell;


import com.finalproject.kdiary.service.SpellService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/spell")
@RequiredArgsConstructor
public class SpellController {

    private final SpellService spellService;

    @GetMapping()
    public JSONObject spellCheck() throws ParseException, IOException {
        JSONObject result = spellService.getResult();
        System.out.println("hi");

        return result;
    }
}