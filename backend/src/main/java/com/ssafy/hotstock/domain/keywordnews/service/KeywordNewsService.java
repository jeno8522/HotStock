package com.ssafy.hotstock.domain.keywordnews.service;

import com.ssafy.hotstock.domain.keyword.domain.Keyword;
import com.ssafy.hotstock.domain.keywordnews.domain.KeywordNews;
import com.ssafy.hotstock.domain.news.domain.News;

import java.util.List;

public interface KeywordNewsService {

    void insertKeywordNews(Keyword keyword, News news);


    List<KeywordNews> getKeywordNewsByKeywordId(Long keywordId);

    List<KeywordNews> getKeywordNewsByNewsId(Long newsId);
}
