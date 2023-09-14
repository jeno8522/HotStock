package com.ssafy.hotstock.domain.keywordtheme.domain;

import com.ssafy.hotstock.domain.keywordtheme.domain.KeywordTheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KeywordThemeRepository extends JpaRepository<KeywordTheme, Long> {
    List<KeywordTheme> findAllById(Long id);

    List<KeywordTheme> findByKeywordId(Long keywordId);

    List<KeywordTheme> findByThemeId(Long themeId);
}
