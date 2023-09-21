package com.ssafy.hotstock.domain.keyword.service;

import com.ssafy.hotstock.domain.keyword.domain.Keyword;

import com.ssafy.hotstock.domain.keyword.dto.KeywordDetailResponseDto;
import java.security.Key;
import java.util.List;
import java.util.Optional;

public interface KeywordService {

    // 키워드 생성
    Keyword insertKeyword(Keyword keyword);

    Keyword findKeywordByContent(String content);

    List<Keyword> getTopKeywordsByCount();

    String getKeywordContent(Long keywordId);


}
