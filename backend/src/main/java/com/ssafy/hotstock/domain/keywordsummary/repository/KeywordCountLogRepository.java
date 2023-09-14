package com.ssafy.hotstock.domain.keywordsummary.repository;

import com.ssafy.hotstock.domain.keywordsummary.domain.KeywordCountLog;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordCountLogRepository extends JpaRepository<KeywordCountLog, Long> {

    List<KeywordCountLog> findByKeywordCheckPointId(Long checkPointId);
}
