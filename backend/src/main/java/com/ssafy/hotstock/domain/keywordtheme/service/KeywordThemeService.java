package com.ssafy.hotstock.domain.keywordtheme.service;

import com.ssafy.hotstock.domain.keywordtheme.domain.KeywordTheme;
import java.util.List;
import java.util.Optional;

public interface KeywordThemeService {
    KeywordTheme insertKeywordTheme(KeywordTheme keywordTheme);
    Optional<KeywordTheme> getKeywordThemeById(Long id);
    List<KeywordTheme> getAllKeywordThemes();
    KeywordTheme updateKeywordTheme(KeywordTheme keywordTheme);
    void deleteKeywordTheme(Long id);
}
