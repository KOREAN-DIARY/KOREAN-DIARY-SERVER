package com.finalproject.kdiary.service;

import com.finalproject.kdiary.controller.speaking.dto.SpeakingResponseDto;
import com.finalproject.kdiary.controller.speaking.dto.SpeakingRequestDto;
import com.finalproject.kdiary.exception.ErrorStatus;
import com.finalproject.kdiary.exception.model.CustomException;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class SpeakingService {
    @Value("${api-key}")
    private String accessKey;

    public SpeakingResponseDto createSpeakingScore(SpeakingRequestDto scoreRequest) {
        String openApiURL = "http://aiopen.etri.re.kr:8000/WiseASR/PronunciationKor";
        String languageCode = "korean";     // 언어 코드
        String script = scoreRequest.getScript();    // 평가 대본
        String audioContents = null;
        MultipartFile audioFile = scoreRequest.getAudio();

        Gson gson = new Gson();

        Map<String, Object> request = new HashMap<>();
        Map<String, String> argument = new HashMap<>();

        try {
            byte[] audioBytes = audioFile.getBytes();
            audioContents = Base64.getEncoder().encodeToString(audioBytes);
        } catch (IOException e) {
            throw new CustomException(ErrorStatus.INVALID_AUDIO_FILE);
        }

        argument.put("language_code", languageCode);
        argument.put("script", script);
        argument.put("audio", audioContents);

        request.put("argument", argument);

        URL url;
        SpeakingResponseDto response = null;
        try {
            url = new URL(openApiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("Authorization", accessKey);

            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.write(gson.toJson(request).getBytes("UTF-8"));
            wr.flush();
            wr.close();

            InputStream is = con.getInputStream();
            byte[] buffer = new byte[is.available()];
            int byteRead = is.read(buffer);
            JSONParser parser = new JSONParser();
            System.out.println(new String(buffer));
            JSONObject jsonObject = (JSONObject) parser.parse(new String(buffer));
            JSONObject result = (JSONObject) jsonObject.get("return_object");

            response = SpeakingResponseDto.of(result.get("recognized").toString(), Double.parseDouble(result.get("score").toString()));

        } catch (IOException e) {
            throw new CustomException(ErrorStatus.FAIL_TO_CRAWL_SPEAKING_PAGE);
        } catch (ParseException e) {
            throw new CustomException(ErrorStatus.FAIL_TO_PARSE_SPEAKING_JSON);
        }
        return response;
    }
}
