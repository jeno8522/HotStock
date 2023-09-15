package com.ssafy.hotstock.domain.stocktheme.service;

import com.ssafy.hotstock.domain.stock.domain.Stock;
import com.ssafy.hotstock.domain.stocktheme.domain.StockTheme;
import com.ssafy.hotstock.domain.stocktheme.dto.StockThemeResponseDto;
import java.util.List;

public interface StockThemeService {
    void insertStockTheme(List<StockThemeResponseDto> stockThemeResponseDtoList);
    StockTheme findStockThemeById(Long id);
    List<StockTheme> findAll();
    void delete(StockTheme stockTheme);

    List<StockTheme> findStockThemesByThemeId(Long themeId);
    List<Stock> getStockFromStockThemes(List<StockTheme> stockThemes);
}