package com.ssafy.hotstock.domain.news.service;

import com.ssafy.hotstock.domain.news.domain.News;
import com.ssafy.hotstock.domain.news.dto.KeywordResponseDto;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface NewsService {

    News crawlingNews(int mediaCompanyNum,int articleNum) throws IOException;
    List<News> crawlingNewsList(int mediaCompanyNum,int articleNum, String currentTime);

    String formatDateTime(String dataTime);

    News createNews(News news);

    List<News> createNewsList(List<News> newsList);

    Optional<News> getNewsById(Long id);

    List<News> getAllNews();

    News updateNews(News news);

    void deleteNews(Long id);

    // 파이썬 서버에 뉴스기사 request -> response로 List<String[keyword, theme]> 받아옴
    KeywordResponseDto fetchKeywords(String title, String content);
}