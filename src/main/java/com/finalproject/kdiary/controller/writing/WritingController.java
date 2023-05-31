package com.finalproject.kdiary.controller.writing;


import com.finalproject.kdiary.controller.writing.dto.WritingRequestDto;
import com.finalproject.kdiary.service.WritingService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping(value = "/writing")
@RequiredArgsConstructor
public class WritingController {

    private final WritingService writingService;

    @PostMapping()
    public JSONObject createWritingScore(@RequestBody @Valid WritingRequestDto request) throws ParseException, IOException {
        JSONObject result = writingService.getResult();

        return result;
    }
}
