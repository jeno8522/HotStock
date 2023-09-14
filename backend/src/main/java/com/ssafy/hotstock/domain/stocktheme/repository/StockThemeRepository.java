package com.ssafy.hotstock.domain.stocktheme.repository;

import com.ssafy.hotstock.domain.stocktheme.domain.StockTheme;
import com.ssafy.hotstock.domain.theme.domain.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockThemeRepository extends JpaRepository<StockTheme, Long> {
    List<StockTheme> findStockThemesByTheme(Theme theme);
}
