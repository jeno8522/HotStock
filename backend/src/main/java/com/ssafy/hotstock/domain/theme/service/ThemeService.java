package com.ssafy.hotstock.domain.theme.service;


import com.ssafy.hotstock.domain.theme.domain.Theme;

import java.util.List;
import java.util.Optional;

public interface ThemeService {
    Theme save(Theme theme);
    Optional<Theme> findById(Long id);
    List<Theme> findAll();
    void delete(Theme theme);

    Theme findThemeByName(String name);
}