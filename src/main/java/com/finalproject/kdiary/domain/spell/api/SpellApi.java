package com.finalproject.kdiary.domain.spell.api;


import lombok.AllArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/spell")
public class SpellApi {

    @GetMapping()
    public String spellCheck() throws ParseException {
        WebDriverUtil example = new WebDriverUtil();
        example.useDriver();

        return "hi";
    }
}
