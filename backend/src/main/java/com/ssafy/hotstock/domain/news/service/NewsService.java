package com.ssafy.hotstock.domain.news.service;

import com.ssafy.hotstock.domain.news.domain.News;
import com.ssafy.hotstock.domain.news.dto.KeywordResponseDto;
import jakarta.persistence.Column;
import java.util.List;
import java.util.Optional;

public interface NewsService {
    List<News> crawlingNews(int mediaCompanyNum,int articleNum);
    News createNews(News news);

    Optional<News> getNewsById(Long id);

    List<News> getAllNews();

    News updateNews(News news);

    void deleteNews(Long id);

    // 파이썬 서버에 뉴스기사 request -> response로 List<String[keyword, theme]> 받아옴
    KeywordResponseDto fetchKeywords(String title, String content);
}