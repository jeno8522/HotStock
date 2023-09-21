package com.ssafy.hotstock.domain.keywordtheme.domain;

import com.ssafy.hotstock.domain.keywordtheme.domain.KeywordTheme;
import com.ssafy.hotstock.domain.theme.domain.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KeywordThemeRepository extends JpaRepository<KeywordTheme, Long> {
    List<KeywordTheme> findAllById(Long id);

    List<KeywordTheme> findByKeywordId(Long keywordId);

    List<KeywordTheme> findByThemeId(Long themeId);

    @Query("SELECT kt FROM KeywordTheme kt JOIN FETCH kt.theme WHERE kt.keyword.id = :keywordId")
    List<KeywordTheme> findByKeywordIdWithTheme(@Param("keywordId") Long keywordId);
}
