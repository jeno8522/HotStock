package com.ssafy.hotstock.domain.keyword.repository;

import com.ssafy.hotstock.domain.keyword.domain.KeywordCheckPoint;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordCheckPointRepository extends JpaRepository<KeywordCheckPoint,Long> {

    List<Long> findIdByCheckTimeBetween(@Param("start")String start, @Param("end")String end);
    Optional<KeywordCheckPoint> findTopByOrderByIdDesc();

}
