package com.ssafy.hotstock.domain.schedule;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.hotstock.domain.keywordnews.service.KeywordNewsService;
import com.ssafy.hotstock.domain.keywordsummary.dto.KeywordSubCountResponseDto;
import com.ssafy.hotstock.domain.keywordsummary.service.KeywordCountLogService;
import com.ssafy.hotstock.domain.keywordtheme.dto.KeywordThemeResponseDto;
import com.ssafy.hotstock.domain.keywordtheme.service.KeywordThemeService;
import com.ssafy.hotstock.domain.news.dto.MediaResponseDto;
import com.ssafy.hotstock.domain.news.dto.NewsResponseDto;
import com.ssafy.hotstock.domain.news.service.MediaService;
import com.ssafy.hotstock.domain.news.service.NewsService;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
    /**
     * 매 10분마다 반복 (cron = "0 0/10 * * * ?")
     */
    @Scheduled(cron = "0 0/10 * * * ?")
    public void updateNews() throws JsonProcessingException {

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
                now);
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
