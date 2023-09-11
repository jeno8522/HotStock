package com.ssafy.hotstock.domain.stocktheme.repository;

import com.ssafy.hotstock.domain.stocktheme.domain.StockTheme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockThemeRepository extends JpaRepository<StockTheme, Long> {
}
