package com.ssafy.hotstock.domain.stocktheme.service;

import com.ssafy.hotstock.domain.stocktheme.domain.StockTheme;
import com.ssafy.hotstock.domain.stock.domain.Stock;
import com.ssafy.hotstock.domain.theme.domain.Theme;
import com.ssafy.hotstock.domain.stocktheme.repository.StockThemeRepository;
import com.ssafy.hotstock.domain.theme.service.ThemeService;
import com.ssafy.hotstock.domain.theme.service.ThemeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class StockThemeServiceImpl implements StockThemeService {

    private final StockThemeRepository stockThemeRepository;

    private final ThemeService themeService;

    @Override
    @Transactional
    public StockTheme save(StockTheme stockTheme) {
        return stockThemeRepository.save(stockTheme);
    }

    @Override
    public Optional<StockTheme> findById(Long id) {
        return stockThemeRepository.findById(id);
    }

    @Override
    public List<StockTheme> findAll() {
        return stockThemeRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(StockTheme stockTheme) {
        stockThemeRepository.delete(stockTheme);
    }

    @Override
    public List<StockTheme> findStockThemesByThemeId(Long themeId) {
        Optional <Theme> themeOptional = themeService.findById(themeId);

        if(themeOptional.isPresent()) {
            Theme theme = themeOptional.get();
            return stockThemeRepository.findStockThemesByTheme(theme);
        } else {
            // 빈 목록 반환
            return new ArrayList<>();

            // 또는
            // throw new RuntimeException("Theme not found with id " + themeId);
        }

    }

    @Override
    public List<Stock> getStockFromStockThemes(List<StockTheme> stockThemes) {
        return stockThemes.stream()
                .map(StockTheme::getStock)
                .toList();
    }
}
