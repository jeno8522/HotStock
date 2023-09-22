package com.ssafy.hotstock.domain.keywordnews.repository;

import com.ssafy.hotstock.domain.keyword.domain.Keyword;
import com.ssafy.hotstock.domain.keywordnews.domain.KeywordNews;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;


//@Repository
@Repository
@Hidden
public interface KeywordNewsRepository extends JpaRepository<KeywordNews, Long> {

    List<KeywordNews> findByKeywordId(Long keywordId);


    List<KeywordNews> findByNewsId(Long newsId);


    @Query("SELECT kn FROM KeywordNews kn JOIN FETCH kn.news WHERE kn.keyword.id = :keywordId")
    List<KeywordNews> findByKeywordIdWithNews(@Param("keywordId") Long keywordId);
}
