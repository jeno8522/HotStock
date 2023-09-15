package com.ssafy.hotstock.domain.stocktheme.service;

import com.ssafy.hotstock.domain.stocktheme.domain.StockTheme;
import com.ssafy.hotstock.domain.stock.domain.Stock;
import com.ssafy.hotstock.domain.stocktheme.dto.StockThemeResponseDto;
import com.ssafy.hotstock.domain.theme.domain.Theme;

import java.util.List;
import java.util.Optional;

public interface StockThemeService {
    void insertStockTheme(List<StockThemeResponseDto> stockThemeResponseDtoList);
    Optional<StockTheme> findById(Long id);
    List<StockTheme> findAll();
    void delete(StockTheme stockTheme);

    List<StockTheme> findStockThemesByThemeId(Long themeId);
    List<Stock> getStockFromStockThemes(List<StockTheme> stockThemes);
}