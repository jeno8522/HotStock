package com.ssafy.hotstock.domain.stocktheme.service;

import com.ssafy.hotstock.domain.stock.service.StockService;
import com.ssafy.hotstock.domain.stocktheme.domain.StockTheme;
import com.ssafy.hotstock.domain.stock.domain.Stock;
import com.ssafy.hotstock.domain.stocktheme.dto.StockThemeResponseDto;
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

    private final StockService stockService;
    @Override
    public void insertStockTheme(List<StockThemeResponseDto> stockThemeResponseDtoList) {

        for (StockThemeResponseDto stockThemeResponseDto : stockThemeResponseDtoList) {
            String themeName = stockThemeResponseDto.getThemeName();
            Theme theme= Theme.builder()
                .name(themeName)
                .build();
            themeService.insertTheme(theme);

            ArrayList<Stock> stockList = new ArrayList<>();

            ArrayList<StockTheme> stockThemeList = new ArrayList<>();
            for (String stockName : stockThemeResponseDto.getStockNames()) {
                Stock stock=Stock.builder()
                    .name(stockName)
                    .build();
                stockList.add(stock);

                StockTheme stockTheme= StockTheme.builder()
                    .stock(stock)
                    .theme(theme)
                    .build();
                stockThemeList.add(stockTheme);
            }

            stockService.insertStockList(stockList);
            stockThemeRepository.saveAll(stockThemeList);
        }
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
