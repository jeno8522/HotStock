package com.ssafy.hotstock.domain.startup;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.hotstock.domain.keyword.service.KeywordService;
import com.ssafy.hotstock.domain.keywordnews.service.KeywordNewsService;
import com.ssafy.hotstock.domain.keywordsummary.dto.KeywordSubCountResponseDto;
import com.ssafy.hotstock.domain.keywordsummary.service.KeywordCountLogService;
import com.ssafy.hotstock.domain.keywordtheme.dto.KeywordThemeResponseDto;
import com.ssafy.hotstock.domain.keywordtheme.service.KeywordThemeService;
import com.ssafy.hotstock.domain.news.domain.News;
import com.ssafy.hotstock.domain.news.dto.NewsResponseDto;
import com.ssafy.hotstock.domain.news.service.NewsService;
import com.ssafy.hotstock.domain.stocktheme.domain.StockTheme;
import com.ssafy.hotstock.domain.stocktheme.dto.StockThemeRequestDto;
import com.ssafy.hotstock.domain.stocktheme.service.StockThemeService;
import java.io.File;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {

    private final StockThemeService stockThemeService;
    private final NewsService newsService;
    private final KeywordCountLogService keywordCountLogService;
    private final KeywordNewsService keywordNewsService;
    private final KeywordThemeService keywordThemeService;
    private final KeywordService keywordService;

    @Override
    public void run(String... args) throws Exception {

        StockTheme stockTheme = stockThemeService.findStockThemeById(1L);

        if (stockTheme == null) {
            // JSON 파일 경로
            String jsonFilePath = "./src/main/resources/stocks.json";

            // ObjectMapper 초기화
            ObjectMapper objectMapper = new ObjectMapper();

            try {
                // JSON 파일을 읽어서 Java 객체로 변환
                List<StockThemeRequestDto> stockThemeRequestDtoList = objectMapper.readValue(
                    new File(jsonFilePath), new TypeReference<List<StockThemeRequestDto>>() {
                    });

                stockThemeService.insertStockTheme(stockThemeRequestDtoList);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        News news = newsService.findNewsById(1L);

        if (news == null) {
            /**
             * 조선, 중앙, 동아, 경향, 한겨레, 한국경제, 매일경제 순
             * */
            int[] mediaCompanyNum = {23, 25, 20, 32, 28, 15, 9};
            int[] articleNum = {3791158, 3311857, 3523490, 3252613, 2658806, 4897987, 5194069,};

            /**
             * 현재 시간 가져오기
             * */
            ZonedDateTime currentTime = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            String format = currentTime.format(formatter);

            /**
             * 전체 기사 가져오기  -2023-09-01 이후부터
             * */
            List<NewsResponseDto> allNewsResponseDtoList = new ArrayList<>();
            for (int i = 0; i < mediaCompanyNum.length; i++) {
//                int articleNum = newsService.findArticleNum(mediaCompanyNum[i]);
                List<NewsResponseDto> newsResponseDtoList = newsService.crawlingNewsList(
                    mediaCompanyNum[i], articleNum[i], format);
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
        }

        keywordService.getKeywordsByCount();
    }
}