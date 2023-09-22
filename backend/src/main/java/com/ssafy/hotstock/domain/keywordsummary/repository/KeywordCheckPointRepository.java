package com.ssafy.hotstock.domain.keywordsummary.repository;

import com.ssafy.hotstock.domain.keywordsummary.domain.KeywordCheckPoint;
import java.util.List;
import java.util.Optional;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
@Hidden
//@RepositoryRestResource
public interface KeywordCheckPointRepository extends JpaRepository<KeywordCheckPoint,Long> {



    List<Long> findIdByCheckTimeBetween(@Param("start")String start, @Param("end")String end);

    Optional<KeywordCheckPoint> findTopByOrderByIdDesc();

}
