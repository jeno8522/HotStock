package com.ssafy.hotstock.domain.stocktheme.repository;

import com.ssafy.hotstock.domain.stocktheme.domain.StockTheme;
import com.ssafy.hotstock.domain.theme.domain.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

public interface StockThemeRepository extends JpaRepository<StockTheme, Long> {
    @Query("SELECT st FROM StockTheme st JOIN FETCH st.stock WHERE st.theme.id = :themeId")
    List<StockTheme> findStockThemesByTheme(Long themeId);
}
