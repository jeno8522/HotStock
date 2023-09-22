package com.ssafy.hotstock.domain.stocktheme.service;

import com.ssafy.hotstock.domain.stock.domain.Stock;
import com.ssafy.hotstock.domain.stock.service.StockService;
import com.ssafy.hotstock.domain.stocktheme.domain.StockTheme;
import com.ssafy.hotstock.domain.stocktheme.dto.StockByThemeIdResponseDto;
import com.ssafy.hotstock.domain.stocktheme.dto.StockThemeResponseDto;
import com.ssafy.hotstock.domain.stocktheme.repository.StockThemeRepository;
import com.ssafy.hotstock.domain.theme.domain.Theme;
import com.ssafy.hotstock.domain.theme.service.ThemeService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            int size=stockThemeResponseDto.getStockNames().size();
            for (int i = 0; i < size; i++) {
                String stockName = stockThemeResponseDto.getStockNames().get(i);
                int stockCode = stockThemeResponseDto.getStockCodes().get(i);
                String stockReason = stockThemeResponseDto.getReasons().get(i);

                Stock stock=Stock.builder()
                    .name(stockName)
                    .code(stockCode)
                    .reason(stockReason)
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
    public StockTheme findStockThemeById(Long id) {
        return stockThemeRepository.findById(id).orElse(null);
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
    public List<StockByThemeIdResponseDto> getStockByThemeId(Long themeId) {

        List<StockTheme> stockThemeList = stockThemeRepository.findStockThemesByTheme(themeId);

        List<StockByThemeIdResponseDto> stockByThemeIdResponseDtoList=new ArrayList<>();

        for (StockTheme stockTheme : stockThemeList) {
            Stock stock = stockTheme.getStock();

            StockByThemeIdResponseDto stockByThemeIdResponseDto=StockByThemeIdResponseDto.builder()
                .stockId(stock.getId())
                .name(stock.getName())
                .code(stock.getCode())
                .reason(stock.getReason())
                .build();
            stockByThemeIdResponseDtoList.add(stockByThemeIdResponseDto);
        }

        return stockByThemeIdResponseDtoList;
    }

    @Override
    public List<Stock> getStockFromStockThemes(List<StockTheme> stockThemes) {
        return stockThemes.stream()
                .map(StockTheme::getStock)
                .toList();
    }
}
