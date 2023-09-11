package com.ssafy.hotstock.domain.news.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.hotstock.domain.news.domain.News;
import com.ssafy.hotstock.domain.news.dto.KeywordResponseDto;
import java.util.List;
import java.util.Optional;

public interface NewsService {
    List<News> crawlingNews(int mediaCompanyNum,int articleNum);

    String formatDateTime(String dataTime);
    News insertNews(News news);

    Optional<News> getNewsById(Long id);

    List<News> getAllNews();

    News updateNews(News news);

    void deleteNews(Long id);

    // 파이썬 서버에 뉴스기사 request -> response로 List<String[keyword, theme]> 받아옴
    void fetchKeywords(List<News> newsList) throws JsonProcessingException;

    // 현웅이 파이썬 서버에서 받은 response로 List<KeywordResponseDto> -> 우리 엔티티에 저장하는 로직
//    void insertKeywordandThemeList(List<KeywordResponseDto> keywordResponseDtoList, News news);
}