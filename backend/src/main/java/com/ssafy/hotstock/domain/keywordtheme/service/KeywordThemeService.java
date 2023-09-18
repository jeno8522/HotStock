package com.ssafy.hotstock.domain.keywordtheme.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.hotstock.domain.keyword.domain.Keyword;
import com.ssafy.hotstock.domain.keywordtheme.domain.KeywordTheme;
import com.ssafy.hotstock.domain.keywordtheme.dto.KeywordThemeResponseDto;
import com.ssafy.hotstock.domain.theme.domain.Theme;

import java.util.List;
import java.util.Optional;

public interface KeywordThemeService {
    KeywordTheme insertKeywordTheme(Keyword keyword, Theme theme);
    Optional<KeywordTheme> getKeywordThemeById(Long id);

    List<KeywordTheme> getKeywordThemeByKeywordId(Long keywordId);
    List<KeywordTheme> getKeywordThemeByThemeId(Long themeId);

    List<KeywordTheme> getAllKeywordThemes();
    KeywordTheme updateKeywordTheme(KeywordTheme keywordTheme);
    void deleteKeywordTheme(Long id);

    List<KeywordThemeResponseDto> fetchKeywordTheme(List<String> keywords) throws JsonProcessingException;

    void insertKeywordTheme(List<KeywordThemeResponseDto> keywordThemeResponseDtos);

    List<Keyword> getKeywordFromKeywordThemes(List<KeywordTheme> keywordThemes);
}
