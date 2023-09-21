package com.ssafy.hotstock.domain.keywordnews.service;

import com.ssafy.hotstock.domain.keywordnews.domain.KeywordNews;
import com.ssafy.hotstock.domain.keywordnews.dto.NewsByKeywordIdResponseDto;
import com.ssafy.hotstock.domain.keywordsummary.dto.KeywordSubCountResponseDto;

import java.util.List;

public interface KeywordNewsService {

    List<String> insertKeywordNews(List<KeywordSubCountResponseDto> keywordSubCountResponseDtoList);

    List<KeywordNews> insertKeywordNewsList(List<KeywordNews> keywordNewsList);

    List<NewsByKeywordIdResponseDto> getNewsByKeywordIdWithNews(Long keywordId);
}
