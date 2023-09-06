package com.ssafy.hotstock.domain.news.service;

import com.ssafy.hotstock.domain.news.domain.News;
import com.ssafy.hotstock.domain.news.domain.NewsRepository;
import java.io.IOException;
import java.util.*;

import com.ssafy.hotstock.domain.news.dto.KeywordResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
    @Override
    public News createNews(News news) {
        return newsRepository.save(news);
    }

    @Override
    public Optional<News> getNewsById(Long id) {
        return newsRepository.findById(id);
    }

    @Override
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    @Override
    public News updateNews(News news) {
        return newsRepository.save(news);  // JPA 에서는 업데이트도 save 사용
    }

    @Override
    public void deleteNews(Long id) {
        newsRepository.deleteById(id);
    }

    // 파이썬 서버에 뉴스기사 request -> response로 List<String[keyword, theme]> 받아옴
    public KeywordResponseDto fetchKeywords(String title, String content) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://your-python-server.com/extract-keywords"; // Python 서버 URL

        // Request Body 구성
        Map<String, String> request = new HashMap<>();
        request.put("title", title);
        request.put("content", content);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(request, headers);

        // HTTP POST 요청 보내기
        ResponseEntity<KeywordResponseDto> response = restTemplate.exchange(url, HttpMethod.POST, entity, KeywordResponseDto.class);

        // Response Body에서 키워드, 관련 theme 리스트 추출
        KeywordResponseDto keywordResponseDto = response.getBody();

        return keywordResponseDto;
    }
}
