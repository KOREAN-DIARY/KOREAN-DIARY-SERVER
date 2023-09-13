package com.finalproject.kdiary.service;

import com.finalproject.kdiary.controller.writing.dto.ErrorInfoDto;
import com.finalproject.kdiary.controller.writing.dto.WritingRequestDto;
import com.finalproject.kdiary.controller.writing.dto.WritingResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class WritingService {
    private WebDriver driver;
    public static String WEB_DRIVER_ID = "webdriver.chrome.driver"; // Properties 설정

    private void chrome() throws IOException {
        String os = System.getProperty("os.name").toLowerCase();
        String WEB_DRIVER_PATH = "";

        if (os.contains("win")) {
            ClassPathResource resource = new ClassPathResource("chromedriver.exe");
            WEB_DRIVER_PATH = Paths.get(resource.getURI()).toString();
        } else if (os.contains("mac")) {
            ClassPathResource resource = new ClassPathResource("chromedriver");
            WEB_DRIVER_PATH = Paths.get(resource.getURI()).toString();
        } else {
            ClassPathResource resource = new ClassPathResource("chromedriver-linux");
            WEB_DRIVER_PATH = Paths.get(resource.getURI()).toString();
        }


        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang=ko");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-default-apps");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.setCapability("ignoreProtectedModeSettings", true);

        // weDriver 생성.
        driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }

    public WritingResponseDto createWritingScore(WritingRequestDto reqeust) throws ParseException, IOException {
        chrome();


        String url = "http://speller.cs.pusan.ac.kr/";
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);  // 페이지 불러오는 여유시간.
        log.info("++++++++++++++++++++++===================+++++++++++++ selenium : " + driver.getTitle());

        driver.findElement(By.xpath("//*[@id='text1']"))
                .sendKeys(reqeust.getScript());
        driver.findElement(By.xpath("//*[@id='btnCheck']")).click();
        List<ErrorInfoDto> responseErrorInfoList = new ArrayList<>();
        boolean hasResult = !driver
                .getPageSource().contains("맞춤법과 문법 오류를 찾지");
        if (hasResult) {
            String text = driver
                    .findElement(By.xpath("/html/head/script[3]"))
                    .getAttribute(("text"))
                    .split("data = ")[1].split(";\n\tpageIdx")[0];
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(text);
            JSONArray array = (JSONArray) obj;
            JSONObject json = (JSONObject) array.get(0);
            JSONArray errorInfoList = (JSONArray) json.get("errInfo");
            errorInfoList.forEach(errorJson -> {
                String help = ((JSONObject) errorJson).get("help").toString();
                String originalString = (String) ((JSONObject) errorJson).get("orgStr");
                String correctWord = (String) ((JSONObject) errorJson).get("candWord");
                responseErrorInfoList.add(new ErrorInfoDto(help, originalString, correctWord));
            });
        }


        quitDriver();

        return WritingResponseDto.of(reqeust.getScript(), responseErrorInfoList);
    }

    private void quitDriver() {
        driver.quit(); // webDriver 종료
    }

}
