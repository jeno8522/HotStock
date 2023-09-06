package com.ssafy.hotstock.domain.news;

import java.util.List;
import java.util.Optional;

public interface NewsService {

    News createNews(News news);

    Optional<News> getNewsById(Long id);

    List<News> getAllNews();

    News updateNews(News news);

    void deleteNews(Long id);

}
