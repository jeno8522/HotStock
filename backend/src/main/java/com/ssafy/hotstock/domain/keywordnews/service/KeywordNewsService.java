package com.ssafy.hotstock.domain.keywordnews.service;

import com.ssafy.hotstock.domain.keyword.domain.Keyword;
import com.ssafy.hotstock.domain.keywordnews.domain.KeywordNews;
import com.ssafy.hotstock.domain.keywordsummary.dto.KeywordSubCountResponseDto;
import com.ssafy.hotstock.domain.news.domain.News;

import java.util.List;

public interface KeywordNewsService {

    List<String> insertKeywordNews(List<KeywordSubCountResponseDto> keywordSubCountResponseDtoList);

    List<KeywordNews> getKeywordNewsByKeywordId(Long keywordId);

    List<KeywordNews> getKeywordNewsByNewsId(Long newsId);

    KeywordNews insertKeywordNews(KeywordNews keywordNews);

}
