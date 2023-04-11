package com.finalproject.kdiary.domain.spell.api;


import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/spell")
public class SpellApi {

    @GetMapping()
    public JSONObject spellCheck() throws ParseException {
        WebDriverUtil example = new WebDriverUtil();
        JSONObject result = example.useDriver();

        return result;
    }
}
