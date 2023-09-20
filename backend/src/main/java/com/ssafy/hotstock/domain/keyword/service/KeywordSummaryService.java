package com.ssafy.hotstock.domain.keyword.service;


import com.ssafy.hotstock.domain.keyword.dto.KeywordSubCountResponseDto;
import com.ssafy.hotstock.domain.news.dto.NewsResponseDto;

import java.util.List;

public interface KeywordSummaryService {

    // 현웅이 파이썬 서버에서 받은 response로 List<KeywordResponseDto> -> 우리 엔티티에 저장하는 로직
    void insertKeywordList(List<KeywordSubCountResponseDto> keywordSubCountResponseDtoList);

    // 파이썬 서버에 뉴스기사 request -> response로 List<String[keyword, theme]> 받아옴
    List<KeywordSubCountResponseDto> fetchKeywords(List<NewsResponseDto> newsResponseDtoList);

}
