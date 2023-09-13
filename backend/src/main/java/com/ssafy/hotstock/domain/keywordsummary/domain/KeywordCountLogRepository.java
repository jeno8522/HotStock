package com.ssafy.hotstock.domain.keywordsummary.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordCountLogRepository extends JpaRepository<KeywordCountLog, Long> {

}
