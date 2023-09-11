package com.ssafy.hotstock.domain.schedule;


import com.ssafy.hotstock.domain.news.domain.Media;
import com.ssafy.hotstock.domain.news.service.MediaService;
import com.ssafy.hotstock.domain.news.service.NewsService;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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

    /**
     * 매 10분마다 반복 (cron = "0 0/10 * * * ?")
     */
    @Scheduled(cron = "0 0/10 * * * ?")
    public void updateNews() {

        /**
         * 현재 시간 가져오기
         * */
        ZonedDateTime currentTime = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String format = currentTime.format(formatter);

        /**
         * 현재 언론사별 가져와야할 기사 번호들 가져오기
         * */
        List<Media> mediaList = mediaService.getAllMedia();

        /**
         * 현재 시간까지 최신 기사들 가져오기
         * */
        for (Media media : mediaList) {
            newsService.crawlingNewsList(media.getMediaCompanyNum(), media.getCurrArticleNum(),
                format);
        }
    }

}
