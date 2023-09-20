package com.ssafy.hotstock.relationships.keywordtheme.repository;

import com.ssafy.hotstock.relationships.keywordtheme.domain.KeywordTheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeywordThemeRepository extends JpaRepository<KeywordTheme, Long> {
    List<KeywordTheme> findAllById(Long id);

    List<KeywordTheme> findByKeywordId(Long keywordId);

    List<KeywordTheme> findByThemeId(Long themeId);
}
