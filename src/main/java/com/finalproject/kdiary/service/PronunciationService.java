package com.finalproject.kdiary.service;

import com.finalproject.kdiary.controller.pronunciation.dto.PronunciationResponseDto;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class PronunciationService {
    @Value("${api-key}")
    private String accessKey;

    public PronunciationResponseDto getPronunciationScore() {
        String openApiURL = "http://aiopen.etri.re.kr:8000/WiseASR/PronunciationKor";
        String languageCode = "korean";     // 언어 코드
        String script = "형제 중에서 맏이가 제일 힘든 것 같아요.";    // 평가 대본
        String audioContents = null;

        Gson gson = new Gson();

        Map<String, Object> request = new HashMap<>();
        Map<String, String> argument = new HashMap<>();

        try {
            ClassPathResource resource = new ClassPathResource("001_034.pcm");
            Path path = Paths.get(resource.getURI());
            byte[] audioBytes = Files.readAllBytes(path);
            audioContents = Base64.getEncoder().encodeToString(audioBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        argument.put("language_code", languageCode);
        argument.put("script", script);
        argument.put("audio", audioContents);

        request.put("argument", argument);

        URL url;
        PronunciationResponseDto response = null;
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

            response = PronunciationResponseDto.of(result.get("recognized").toString(), Double.parseDouble(result.get("score").toString()));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return response;
    }
}
