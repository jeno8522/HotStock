package com.ssafy.hotstock.domain.keywordsummary.domain;

import com.ssafy.hotstock.domain.keywordsummary.domain.KeywordSummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeywordSummaryRepository extends JpaRepository<KeywordSummary, Long> {
}
