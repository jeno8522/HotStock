package com.ssafy.hotstock.domain.keywordtheme.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.hotstock.domain.keyword.domain.Keyword;
import com.ssafy.hotstock.domain.keywordtheme.domain.KeywordTheme;
import com.ssafy.hotstock.domain.keywordtheme.dto.KeywordByThemeIdResponseDto;
import com.ssafy.hotstock.domain.keywordtheme.dto.KeywordThemeResponseDto;
import com.ssafy.hotstock.domain.keywordtheme.dto.ThemeByKeywordIdResponseDto;
import com.ssafy.hotstock.domain.theme.domain.Theme;

import java.util.List;

public interface KeywordThemeService {
    KeywordTheme insertKeywordTheme(Keyword keyword, Theme theme);

    List<KeywordByThemeIdResponseDto> getKeywordByThemeId(Long themeId);

    List<KeywordThemeResponseDto> fetchKeywordTheme(List<String> keywords) throws JsonProcessingException;

    void insertKeywordTheme(List<KeywordThemeResponseDto> keywordThemeResponseDtos);

    List<Keyword> getKeywordFromKeywordThemes(List<KeywordTheme> keywordThemes);

    List<ThemeByKeywordIdResponseDto> getThemeByKeywordId(Long keywordId);

}
