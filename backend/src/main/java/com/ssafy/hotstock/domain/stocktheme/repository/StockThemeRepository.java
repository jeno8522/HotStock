package com.ssafy.hotstock.domain.stocktheme.repository;

import com.ssafy.hotstock.domain.stocktheme.domain.StockTheme;
import com.ssafy.hotstock.domain.theme.domain.Theme;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;


@Repository
@Hidden
public interface StockThemeRepository extends JpaRepository<StockTheme, Long> {


    @Query("SELECT st FROM StockTheme st JOIN FETCH st.stock WHERE st.theme.id = :themeId")
    List<StockTheme> findStockThemesByTheme(Long themeId);

    @Query("SELECT st FROM StockTheme st JOIN FETCH st.theme WHERE st.stock.code = :stockCode")
    List<StockTheme> findStockThemesByStock(int stockCode);

}
