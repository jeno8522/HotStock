package com.ssafy.hotstock.domain.keywordsummary.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordCheckPointRepository extends JpaRepository<KeywordCheckPoint,Long> {

    List<Long> findIdByCheckTimeBetween(String start, String end);


}
