package com.ssafy.hotstock.domain.news.service;

import com.ssafy.hotstock.domain.news.domain.News;
import com.ssafy.hotstock.domain.news.domain.NewsRepository;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
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
                String link =
                    "https://n.news.naver.com/article/" + String.format("%03d", mediaCompanyNum)
                        + "/" + String.format("%010d", articleNum);

                Connection conn = Jsoup.connect(link)
                    .userAgent(
                        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36");

                Document doc = conn.get();

                // 제목 찾아오기
                String title = doc.select("h2#title_area").text();

                // 시사/ 연예
                if (title.isEmpty()) {
                    title = doc.select("h2.end_tit").text();
                }

                // 스포츠
                if (title.isEmpty()) {
                    title = doc.select("h4.title").text();
                }

                // 본문 찾아오기
                String content = doc.select("article#dic_area").text();

                // 시사/ 연예
                if (content.isEmpty()) {
                    Elements elements = doc.select("div#articeBody");
                    if (!elements.isEmpty()) {
                        content = elements.text();
                    } else {
                        System.out.println("해당 게시글의 본문을 찾을 수 없습니다.");
                    }
                }

                // 스포치
                if (content.isEmpty()) {
                    Elements elements = doc.select("div#newsEndContents");
                    if (!elements.isEmpty()) {
                        content = elements.text();
                    } else {
                        System.out.println("해당 게시글의 본문을 찾을 수 없습니다.");
                    }
                }



                // 날짜 찾아오기
                String date = doc.select("span.media_end_head_info_datestamp_time")
                    .attr("data-date-time");

                // 시사/ 연예
                if (date.isEmpty()) {
                    Elements elements = doc.select("span.author em");

                    // 첫 번째 태그의 내용 가져오기
                    if (!elements.isEmpty()) {
                        String dataTime = elements.first().text();
                        date=formatDateTime(dataTime);
                    } else {
                        System.out.println("해당 날짜를 찾을 수 없습니다.");
                    }
                }

                // 스포츠
                if (date.isEmpty()) {
                    Elements elements = doc.select("div.news_headline span");

                    // 첫 번째 태그의 내용 가져오기
                    if (!elements.isEmpty()) {
                        String dataTime = elements.get(1).text();
                        dataTime = dataTime.replace("기사입력", "").trim();
                        date=formatDateTime(dataTime);
                    } else {
                        System.out.println("해당 날짜를 찾을 수 없습니다.");
                    }
                }

                News news = new News();

                news.setTitle(title);
                news.setContent(content);
                news.setLink(link);
                news.setDate(date);
                news.setMediaCompanyNum(mediaCompanyNum);
                news.setArticleNum(articleNum);

                newsList.add(news);

                articleNum++;
//                System.out.println("articleNum = " + articleNum);
            }


        } catch (IOException e) {

            log.error("Jsoup 연결 오류: " + e.getCause());
            throw e;

        } finally {
            newsRepository.saveAll(newsList);

            return newsList;
        }
    }

    public String formatDateTime(String dataTime) {
        try {
            // 주어진 날짜와 시간 형식을 해석
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy.MM.dd. a hh:mm");
            Date date = inputFormat.parse(dataTime);

            // 원하는 형식으로 출력 형식 지정
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
