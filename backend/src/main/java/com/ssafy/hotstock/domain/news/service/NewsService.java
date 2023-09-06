package com.ssafy.hotstock.domain.news.service;

import com.ssafy.hotstock.domain.news.domain.News;
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

}