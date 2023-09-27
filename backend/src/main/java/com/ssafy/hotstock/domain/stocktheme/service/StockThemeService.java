package com.ssafy.hotstock.domain.stocktheme.service;

import com.ssafy.hotstock.domain.stock.domain.Stock;
import com.ssafy.hotstock.domain.stocktheme.domain.StockTheme;
import com.ssafy.hotstock.domain.stocktheme.dto.StockByThemeIdResponseDto;
import com.ssafy.hotstock.domain.stocktheme.dto.StockThemeRequestDto;
import com.ssafy.hotstock.domain.stocktheme.dto.ThemeByStockCodeResponseDto;
import java.util.List;

public interface StockThemeService {
    void insertStockTheme(List<StockThemeRequestDto> stockThemeRequestDtoList);
    StockTheme findStockThemeById(Long id);
    List<StockTheme> findAll();
    void delete(StockTheme stockTheme);

    List<StockByThemeIdResponseDto> getStockByThemeId(Long themeId);
    List<ThemeByStockCodeResponseDto> getThemeByStockCode(int code);
    List<Stock> getStockFromStockThemes(List<StockTheme> stockThemes);
}