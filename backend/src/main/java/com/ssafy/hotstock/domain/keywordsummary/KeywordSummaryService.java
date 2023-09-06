package com.ssafy.hotstock.domain.keywordsummary;

import java.util.List;
import java.util.Optional;

public interface KeywordSummaryService {

    KeywordSummary createKeywordSummary(KeywordSummary keywordSummary);

    Optional<KeywordSummary> getKeywordSummaryById(Long id);

    List<KeywordSummary> getAllKeywordSummary();

    KeywordSummary updateKeywordSummary(KeywordSummary keywordSummary);

    void deleteKeywordSummary(Long id);

}
