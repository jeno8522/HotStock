package com.ssafy.hotstock.domain.schedule;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.hotstock.domain.keyword.service.KeywordService;
import com.ssafy.hotstock.domain.keywordnews.service.KeywordNewsService;
import com.ssafy.hotstock.domain.keywordsummary.dto.KeywordSubCountResponseDto;
import com.ssafy.hotstock.domain.keywordsummary.service.KeywordCountLogService;
import com.ssafy.hotstock.domain.keywordtheme.dto.KeywordThemeResponseDto;
import com.ssafy.hotstock.domain.keywordtheme.service.KeywordThemeService;
import com.ssafy.hotstock.domain.news.dto.MediaResponseDto;
import com.ssafy.hotstock.domain.news.dto.NewsResponseDto;
import com.ssafy.hotstock.domain.news.service.MediaService;
import com.ssafy.hotstock.domain.news.service.NewsService;
import java.io.File;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@Configuration
@RequiredArgsConstructor
public class ScheduleTask {

    private final NewsService newsService;
    private final MediaService mediaService;
    private final KeywordCountLogService keywordCountLogService;
    private final KeywordNewsService keywordNewsService;
    private final KeywordThemeService keywordThemeService;
    private final KeywordService keywordService;

    /**
     * 매 10분마다 반복 (cron = "0 0/10 * * * ?")
     */
    @Scheduled(cron = "0 0/10 * * * ?")
    public void updateNews() throws JsonProcessingException {

        keywordService.clearKeywordCache();

        // 로컬에서
//        System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver_window.exe");
        // 서버에 올릴 때
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");

//        ChromeDriverService.Builder serviceBuilder = new ChromeDriverService.Builder();
//        serviceBuilder.usingDriverExecutable(new File("/usr/bin/chromedriver"));
//        ChromeDriverService service = serviceBuilder.usingPort(4444).build();

//        try {
//            service.start();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        // ChromeDriver 옵션 설정
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-gpu");
        options.addArguments("no-sandbox");
        options.addArguments("disable-dev-shm-usage");
        options.addArguments("--remote-debugging-port=4444");

        // WebDriver 객체 생성
        WebDriver driver = new ChromeDriver(options);

        File dirFile = new File("/usr/bin");
        File[] fileList = dirFile.listFiles();
        for (File file : fileList) {
            System.out.println(file.getName());
        }

        /**
         * 현재 시간 가져오기
         * */
        ZonedDateTime currentTime = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String now = currentTime.format(formatter);

        /**
         * 현재 언론사별 가져와야할 기사 번호들 가져오기
         * */
        List<MediaResponseDto> mediaResponseDtoList = mediaService.getAllMedia();

        /**
         * 현재 시간까지 최신 기사들 가져오기
         * */
        List<NewsResponseDto> allNewsResponseDtoList = new ArrayList<>();

        for (MediaResponseDto mediaResponseDto : mediaResponseDtoList) {
            List<NewsResponseDto> newsResponseDtoList = newsService.crawlingNewsList(
                mediaResponseDto.getMediaCompanyNum(),
                mediaResponseDto.getCurrArticleNum(),
                now, driver);
            allNewsResponseDtoList.addAll(newsResponseDtoList);
        }

        /**
         * 가져온 기사들을 사용해 키워드 추출 및 저장
         * KeywordCheckPoint, KeywordCountLog 저장
         */
        List<KeywordSubCountResponseDto> keywordSubCountResponseDtoList = keywordCountLogService.fetchKeywords(
            allNewsResponseDtoList);

        /**
         * Keyword, KeywordNews 저장
         */
        List<String> newKeywordList = keywordNewsService.insertKeywordNews(
            keywordSubCountResponseDtoList);

        /**
         * 새로 발생한 키워드 리스트를 파이썬 서버에 보내 response 받아오기
         */
        List<KeywordThemeResponseDto> keywordThemeResponseDtoList = keywordThemeService.fetchKeywordTheme(
            newKeywordList);

        /**
         * 새로 발생한 키워드를 테마와 묶어서 저장
         */
        keywordThemeService.insertKeywordTheme(keywordThemeResponseDtoList);

        keywordService.clearKeywordCache();

        keywordService.getKeywordsByCount();
    }
}
