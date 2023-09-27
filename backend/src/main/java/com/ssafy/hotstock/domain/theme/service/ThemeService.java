package com.ssafy.hotstock.domain.theme.service;


import com.ssafy.hotstock.domain.theme.domain.Theme;
import com.ssafy.hotstock.domain.theme.dto.ThemeResponseDto;

import java.util.List;
import java.util.Optional;

public interface ThemeService {
    Theme insertTheme(Theme theme);
    Optional<Theme> findById(Long id);
    List<ThemeResponseDto> getAllThemes();
    void delete(Theme theme);

    Theme findThemeByName(String name);
}