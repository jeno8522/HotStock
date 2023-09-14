package com.ssafy.hotstock.domain.keywordnews.repository;

import com.ssafy.hotstock.domain.keywordnews.domain.KeywordNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface KeywordNewsRepository extends JpaRepository<KeywordNews, Long> {
    List<KeywordNews> findByKeywordId(Long keywordId);

    List<KeywordNews> findByNewsId(Long newsId);
}
