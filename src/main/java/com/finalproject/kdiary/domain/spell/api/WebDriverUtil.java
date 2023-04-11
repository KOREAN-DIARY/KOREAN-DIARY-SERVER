package com.finalproject.kdiary.domain.spell.api;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class WebDriverUtil {

    private WebDriver driver;
    public static String WEB_DRIVER_ID = "webdriver.chrome.driver"; // Properties 설정
    public static String WEB_DRIVER_PATH = "/Users/anchaelin/Desktop/2023-final-project/KOREAN-DIARY-SERVER/chromedriver"; // WebDriver 경로

    public WebDriverUtil() {
        chrome();
    }

    private void chrome() {
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

        // webDriver 옵션 설정.
        ChromeOptions options = new ChromeOptions();
//        options.setHeadless(true);
        options.addArguments("--lang=ko");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.setCapability("ignoreProtectedModeSettings", true);

        // weDriver 생성.
        driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }

    public void useDriver() throws ParseException {
        String url = "http://speller.cs.pusan.ac.kr/";
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);  // 페이지 불러오는 여유시간.
        log.info("++++++++++++++++++++++===================+++++++++++++ selenium : " + driver.getTitle());

        driver.findElement(By.xpath("//*[@id='text1']"))
                .sendKeys("심여를 기우려 만든 마춤뻡 검사기");
        driver.findElement(By.xpath("//*[@id='btnCheck']")).click();
        String text = driver.findElement(By.xpath("/html/head/script[3]"))
                .getAttribute(("text"));
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(text.split("data = ")[1].split(";")[0]);
        JSONArray array = (JSONArray) obj;
        JSONObject json = (JSONObject) array.get(0);
        System.out.println(json);

        quitDriver();
    }

    private void quitDriver() {
        driver.quit(); // webDriver 종료
    }

}
