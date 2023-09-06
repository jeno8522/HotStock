package com.ssafy.hotstock.domain.keyword.service;

import com.ssafy.hotstock.domain.keyword.domain.Keyword;

import java.util.List;
import java.util.Optional;

public interface KeywordService {

    // 키워드 생성
    Keyword createKeyword(Keyword keyword);

    // 키워드 ID로 조회
    Optional<Keyword> getKeywordById(Long id);

    // 모든 키워드 조회
    List<Keyword> getAllKeywords();

    // 키워드 업데이트
    Keyword updateKeyword(Keyword keyword);

    // 키워드 삭제
    void deleteKeyword(Long id);

}
