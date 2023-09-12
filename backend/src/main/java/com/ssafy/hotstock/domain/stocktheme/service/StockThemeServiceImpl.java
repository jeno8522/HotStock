package com.ssafy.hotstock.domain.stocktheme.service;

import com.ssafy.hotstock.domain.stocktheme.domain.StockTheme;
import com.ssafy.hotstock.domain.stock.domain.Stock;
import com.ssafy.hotstock.domain.theme.domain.Theme;
import com.ssafy.hotstock.domain.stocktheme.repository.StockThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class StockThemeServiceImpl implements StockThemeService {

    private final StockThemeRepository stockThemeRepository;

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
}
