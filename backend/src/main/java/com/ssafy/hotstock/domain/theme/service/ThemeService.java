package com.ssafy.hotstock.domain.theme.service;

import com.ssafy.hotstock.domain.theme.domain.Theme;

import java.util.List;
import java.util.Optional;

public interface ThemeService {
    Theme insertTheme(Theme theme);

    // 키워드 ID로 조회
    Optional<Theme> getThemeById(Long id);

    // 모든 키워드 조회
    List<Theme> getAllThemes();

    // 키워드 업데이트
    Theme updateTheme(Long id, Theme updatedTheme);

    // 키워드 삭제
    void deleteTheme(Long id);
}
