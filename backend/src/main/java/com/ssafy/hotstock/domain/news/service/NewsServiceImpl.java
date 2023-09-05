package com.ssafy.hotstock.domain.news.service;

import com.ssafy.hotstock.domain.news.domain.News;
import com.ssafy.hotstock.domain.news.domain.NewsRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    @Override
    public List<News> crawlingNews(int mediaCompanyNum, int articleNum) {

        List<News> newsList = new ArrayList<>();
        try {

            for (int i = 0; i < 100; i++) {
                String link = "https://n.news.naver.com/article/" + String.format("%03d", mediaCompanyNum) + "/" + String.format("%010d", articleNum);

                Document doc = Jsoup.connect(link).get();

                String title = doc.select("h2#title_area").text();
                String content = doc.select("article#dic_area").text();
                String date = doc.select("span.media_end_head_info_datestamp_time")
                    .attr("data-date-time");

                News news = new News();

                news.setTitle(title);
                news.setContent(content);
                news.setLink(link);
                news.setDate(date);
                news.setMediaCompanyNum(mediaCompanyNum);
                news.setArticleNum(articleNum);

                newsList.add(news);

                articleNum++;
            }


        } catch (IOException e) {

            log.error("Jsoup 연결 오류: " + e.getCause());
            throw e;

        }finally {
            newsRepository.saveAll(newsList);

            return newsList;
        }
    }
}
