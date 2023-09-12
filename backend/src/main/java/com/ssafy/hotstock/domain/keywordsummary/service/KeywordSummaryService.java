package com.ssafy.hotstock.domain.keywordsummary.service;

import com.ssafy.hotstock.domain.keywordsummary.dto.KeywordResponseDto;
import java.util.List;

public interface KeywordSummaryService {

    // 현웅이 파이썬 서버에서 받은 response로 List<KeywordResponseDto> -> 우리 엔티티에 저장하는 로직
    void insertKeywordList(List<KeywordResponseDto> keywordResponseDtoList);
}
