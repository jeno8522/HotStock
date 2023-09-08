package com.ssafy.hotstock.domain.startup;

import com.ssafy.hotstock.domain.news.service.NewsService;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {

    private final NewsService newsService;

    @Override
    public void run(String... args) throws Exception {
        /**
         * 조선, 중앙, 동아, 경향, 한겨레, 한국경제, 매일경제 순
         * */
        int[] mediaCompanyNum = {23, 25, 20, 32, 28, 15, 9};
        int[] articleNum = {3786617, 3306317, 3518828, 3247888, 2655741, 4889467, 5183849,};

        /**
         * 현재 시간 가져오기
         * */
        ZonedDateTime currentTime = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String format = currentTime.format(formatter);

        /**
         * 전체 기사 가져오기  -2023-09-01 이후부터
         * */
        for (int i = 0; i < mediaCompanyNum.length; i++) {
            newsService.crawlingNewsList(mediaCompanyNum[i], articleNum[i], format);
        }
    }
}