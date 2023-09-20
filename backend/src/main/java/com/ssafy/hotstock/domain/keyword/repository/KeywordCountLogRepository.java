package com.ssafy.hotstock.domain.keyword.repository;

import com.ssafy.hotstock.domain.keyword.domain.KeywordCountLog;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordCountLogRepository extends JpaRepository<KeywordCountLog, Long> {

    List<KeywordCountLog> findByKeywordCheckPointId(Long checkPointId);
}
