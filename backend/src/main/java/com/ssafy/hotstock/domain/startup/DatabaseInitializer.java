package com.ssafy.hotstock.domain.startup;

import com.ssafy.hotstock.domain.keywordnews.service.KeywordNewsService;
import com.ssafy.hotstock.domain.keywordsummary.dto.KeywordSubCountResponseDto;
import com.ssafy.hotstock.domain.keywordsummary.service.KeywordSummaryService;
import com.ssafy.hotstock.domain.keywordtheme.dto.KeywordThemeResponseDto;
import com.ssafy.hotstock.domain.keywordtheme.service.KeywordThemeService;
import com.ssafy.hotstock.domain.news.dto.NewsResponseDto;
import com.ssafy.hotstock.domain.news.service.NewsService;
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

    private final NewsService newsService;
    private final KeywordSummaryService keywordSummaryService;
    private final KeywordNewsService keywordNewsService;
    private final KeywordThemeService keywordThemeService;

    @Override
    public void run(String... args) throws Exception {
        /**
         * 조선, 중앙, 동아, 경향, 한겨레, 한국경제, 매일경제 순
         * */
//        int[] mediaCompanyNum = {23, 25, 20, 32, 28, 15, 9};
        int[] mediaCompanyNum = {23};
//        int[] articleNum = {3787327, 3306318, 3518829, 3247930, 2656210, 4890738, 5185375,};

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
            int articleNum = newsService.findArticleNum(mediaCompanyNum[i]);
            List<NewsResponseDto> newsResponseDtoList = newsService.crawlingNewsList(
                mediaCompanyNum[i], articleNum, format);
            allNewsResponseDtoList.addAll(newsResponseDtoList);
        }

        /**
         * 가져온 기사들을 사용해 키워드 추출 및 저장
         * KeywordCheckPoint, KeywordCountLog 저장
         */
        List<KeywordSubCountResponseDto> keywordSubCountResponseDtoList = keywordSummaryService.fetchKeywords(
            allNewsResponseDtoList);

        /**
         * Keyword, KeywordNews 저장
         */
        List<String> newKeywordList = keywordNewsService.insertKeywordNews(keywordSubCountResponseDtoList);

        /**
         * 새로 발생한 키워드 리스트를 파이썬 서버에 보내 response 받아오기
         */
        List<KeywordThemeResponseDto> keywordThemeResponseDtoList=keywordThemeService.fetchKeywordTheme(newKeywordList);

        /**
         * 새로 발생한 키워드를 테마와 묶어서 저장
         */
        keywordThemeService.insertKeywordTheme(keywordThemeResponseDtoList);
    }
}