package com.ssafy.hotstock.domain.keywordtheme.service;

import com.ssafy.hotstock.domain.keyword.domain.Keyword;
import com.ssafy.hotstock.domain.keywordtheme.domain.KeywordTheme;
import com.ssafy.hotstock.domain.theme.domain.Theme;

import java.util.List;
import java.util.Optional;

public interface KeywordThemeService {
    KeywordTheme insertKeywordTheme(Keyword keyword, Theme theme);
    Optional<KeywordTheme> getKeywordThemeById(Long id);
    List<KeywordTheme> getAllKeywordThemes();
    KeywordTheme updateKeywordTheme(KeywordTheme keywordTheme);
    void deleteKeywordTheme(Long id);
}
