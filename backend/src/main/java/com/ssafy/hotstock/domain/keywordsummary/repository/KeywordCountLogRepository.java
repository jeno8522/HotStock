package com.ssafy.hotstock.domain.keywordsummary.repository;

import com.ssafy.hotstock.domain.keywordsummary.domain.KeywordCountLog;
import java.util.List;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

//@Repository
@Repository
@Hidden
public interface KeywordCountLogRepository extends JpaRepository<KeywordCountLog, Long> {


    List<KeywordCountLog> findByKeywordCheckPointId(Long checkPointId);
}
