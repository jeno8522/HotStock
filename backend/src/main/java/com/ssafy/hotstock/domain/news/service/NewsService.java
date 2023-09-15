package com.ssafy.hotstock.domain.news.service;

import com.ssafy.hotstock.domain.news.domain.News;
import com.ssafy.hotstock.domain.news.dto.NewsResponseDto;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface NewsService {

    int findArticleNum(int mediaCompanyNum);

    News crawlingNews(int mediaCompanyNum, int articleNum) throws IOException;

    List<NewsResponseDto> crawlingNewsList(int mediaCompanyNum, int articleNum, String currentTime);

    String formatDateTime(String dataTime);

    News insertNews(News news);

    List<News> createNewsList(List<News> newsList);

    News getNewsById(Long id);

    List<News> getAllNews();

    News updateNews(News news);

    void deleteNews(Long id);


}