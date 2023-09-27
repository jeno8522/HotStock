package com.ssafy.hotstock.domain.keyword.service;

import com.ssafy.hotstock.domain.keyword.domain.Keyword;

import com.ssafy.hotstock.domain.keyword.dto.TopKeywordsResponseDto;
import java.util.List;

public interface KeywordService {

    // 키워드 생성
    Keyword insertKeyword(Keyword keyword);

    Keyword findKeywordByContent(String content);

    List<TopKeywordsResponseDto> getKeywordsByCount();

    String getKeywordContent(Long keywordId);

    void clearKeywordCache();
}
