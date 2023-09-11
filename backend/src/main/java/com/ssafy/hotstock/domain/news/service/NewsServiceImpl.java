package com.ssafy.hotstock.domain.news.service;


import com.ssafy.hotstock.domain.keyword.domain.Keyword;
import com.ssafy.hotstock.domain.keyword.service.KeywordService;
import com.ssafy.hotstock.domain.keywordsummary.domain.KeywordSummary;
import com.ssafy.hotstock.domain.keywordtheme.domain.KeywordTheme;
import com.ssafy.hotstock.domain.keywordtheme.service.KeywordThemeService;
import com.ssafy.hotstock.domain.news.domain.News;
import com.ssafy.hotstock.domain.news.domain.NewsRepository;
import com.ssafy.hotstock.domain.news.dto.KeywordResponseDto;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.ssafy.hotstock.domain.stock.domain.Stock;
import com.ssafy.hotstock.domain.theme.domain.Theme;
import com.ssafy.hotstock.domain.theme.service.ThemeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
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

    private final KeywordThemeService keywordThemeService;

    private final KeywordService keywordService;

    private final ThemeService themeService;

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
                        date = formatDateTime(dataTime);
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
                        date = formatDateTime(dataTime);
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

    @Override
    public News insertNews(News news) {
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

    // 파이썬 서버에 뉴스기사 request -> response로 List<KeywordResponseDto> 받아옴
    public void fetchKeywords(News news) {
        String title = news.getTitle();
        String content = news.getContent();
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
        ResponseEntity<List<KeywordResponseDto>> response = restTemplate.exchange(url, HttpMethod.POST,
                entity, new ParameterizedTypeReference<List<KeywordResponseDto>>() {
                });

        // Response Body에서 키워드, 관련 theme 리스트 추출
        List<KeywordResponseDto> keywordResponseDtoList = response.getBody();

        
        //예외 처리 추가해야함유
//        assert keywordResponseDtoList != null;
//        if (keywordResponseDtoList == null) {
//
//        } else {
//            insertKeywordandThemeList(keywordResponseDtoList, news);
//        }
    }


    
    // 현웅이 파이썬 서버에서 받은 response로 List<KeywordResponseDto> -> 우리 엔티티에 저장하는 로직
//    @Override
//    public void insertKeywordandThemeList(List<KeywordResponseDto> keywordResponseDtoList, News news) {
//
//        for (KeywordResponseDto keywordResponseDto : keywordResponseDtoList
//        ) {
//            String keywordContent = keywordResponseDto.getKeywordContent();
//            List<String> themeNames = keywordResponseDto.getThemeNames();
//
//            //수정 예정
//            List<Stock> stocks = new ArrayList<>();
//
//            LocalDateTime createDate = LocalDateTime.now();
//
//            Keyword keyword = Keyword.builder()
//                    .content(keywordContent)
//                    .createDate(createDate)
//                    .news(news)
//                    .build();
//            keywordService.insertKeyword(keyword);
//
//            //수정 예정
//            KeywordSummary keywordSummary = KeywordSummary.builder()
//                    .count(1L)
//                    .createDate(createDate)
//                    .keyword(keyword)
//                    .build();
//            keyword.setKeywordSummary(keywordSummary);
//
//            for (String themeName : themeNames) {
//                Theme theme = Theme.builder()
//                        .name(themeName)
//                        .stocks(stocks)
//                        .build();
//                themeService.insertTheme(theme);
//                KeywordTheme keywordTheme = KeywordTheme.builder()
//                        .keyword(keyword)
//                        .theme(theme)
//                        .build();
//                keywordThemeService.insertKeywordTheme(keywordTheme);
//            }
//        }
//    }
}
