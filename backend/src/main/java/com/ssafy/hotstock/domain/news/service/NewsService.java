package com.ssafy.hotstock.domain.news.service;

import com.ssafy.hotstock.domain.news.domain.News;
import jakarta.persistence.Column;
import java.util.List;

public interface NewsService {
    List<News> crawlingNews(int mediaCompanyNum,int articleNum);

    String formatDateTime(String dataTime);
}