package com.ssafy.hotstock.relationships.keywordnews.service;

import com.ssafy.hotstock.relationships.keywordnews.domain.KeywordNews;
import com.ssafy.hotstock.domain.keyword.dto.KeywordSubCountResponseDto;

import java.util.List;

public interface KeywordNewsService {

    List<String> insertKeywordNews(List<KeywordSubCountResponseDto> keywordSubCountResponseDtoList);

    List<KeywordNews> getKeywordNewsByKeywordId(Long keywordId);

    List<KeywordNews> getKeywordNewsByNewsId(Long newsId);

    List<KeywordNews> insertKeywordNewsList(List<KeywordNews> keywordNewsList);

}
