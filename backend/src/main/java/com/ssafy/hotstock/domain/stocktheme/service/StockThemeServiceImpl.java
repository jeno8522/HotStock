package com.ssafy.hotstock.domain.stocktheme.service;

import com.ssafy.hotstock.domain.stock.domain.Stock;
import com.ssafy.hotstock.domain.stock.dto.StockByCodeNameResponseDto;
import com.ssafy.hotstock.domain.stock.service.StockService;
import com.ssafy.hotstock.domain.stocktheme.domain.StockTheme;
import com.ssafy.hotstock.domain.stocktheme.dto.StockByThemeIdResponseDto;
import com.ssafy.hotstock.domain.stocktheme.dto.StockThemeRequestDto;
import com.ssafy.hotstock.domain.stocktheme.dto.ThemeByStockCodeResponseDto;
import com.ssafy.hotstock.domain.stocktheme.repository.StockThemeRepository;
import com.ssafy.hotstock.domain.theme.domain.Theme;
import com.ssafy.hotstock.domain.theme.service.ThemeService;

import com.ssafy.hotstock.global.advice.exception.StockFoundException;
import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class StockThemeServiceImpl implements StockThemeService {

    private final StockThemeRepository stockThemeRepository;

    private final ThemeService themeService;

    private final StockService stockService;

    @Override
    public void insertStockTheme(List<StockThemeRequestDto> stockThemeRequestDtoList) {

        for (StockThemeRequestDto stockThemeRequestDto : stockThemeRequestDtoList) {
            String themeName = stockThemeRequestDto.getThemeName();
            Theme theme = Theme.builder()
                    .name(themeName)
                    .build();
            themeService.insertTheme(theme);

            ArrayList<Stock> stockList = new ArrayList<>();

            ArrayList<StockTheme> stockThemeList = new ArrayList<>();
            int size = stockThemeRequestDto.getStockNames().size();
            for (int i = 0; i < size; i++) {
                String stockName = stockThemeRequestDto.getStockNames().get(i);
                int stockCode = stockThemeRequestDto.getStockCodes().get(i);
                String stockReason = stockThemeRequestDto.getReasons().get(i);

                Stock stock = Stock.builder()
                        .name(stockName)
                        .code(stockCode)
                        .build();
                stockList.add(stock);

                StockTheme stockTheme = StockTheme.builder()
                        .stock(stock)
                        .theme(theme)
                        .reason(stockReason)
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

        List<StockByThemeIdResponseDto> stockByThemeIdResponseDtoList = new ArrayList<>();

        for (StockTheme stockTheme : stockThemeList) {
            Stock stock = stockTheme.getStock();

            List<StockByCodeNameResponseDto> stockDetailsFromPython = stockService.getStockDetailsFromPython(
                    stock.getCode());

            StockByThemeIdResponseDto stockByThemeIdResponseDto = StockByThemeIdResponseDto.builder()
                .name(stock.getName())
                .code(String.format("%06d", stock.getCode()))
                .reason(stockTheme.getReason())
                .market_sum(stockDetailsFromPython.get(0).getMarket_sum())
                .price_now(stockDetailsFromPython.get(0).getPrice_now())
                .price_rate(stockDetailsFromPython.get(0).getPrice_rate())
                .price_high(stockDetailsFromPython.get(0).getPrice_high())
                .price_low(stockDetailsFromPython.get(0).getPrice_low())
                .amount(stockDetailsFromPython.get(0).getAmount())
                .price_diff(stockDetailsFromPython.get(0).getPrice_diff())
                .build();
            stockByThemeIdResponseDtoList.add(stockByThemeIdResponseDto);
        }

        return stockByThemeIdResponseDtoList;
    }

    @Override
    public List<ThemeByStockCodeResponseDto> getThemeByStockCode(int code) {

        List<ThemeByStockCodeResponseDto> themeByStockCodeResponseDtoList = new ArrayList<>();
        try {
            List<StockTheme> stockThemeList = stockThemeRepository.findStockThemesByStock(code);

            for (StockTheme stockTheme : stockThemeList) {
                ThemeByStockCodeResponseDto theme= ThemeByStockCodeResponseDto.builder()
                    .name(stockTheme.getTheme().getName())
                    .build();
                themeByStockCodeResponseDtoList.add(theme);
            }
        } catch (NotFoundException e) {
            log.error("입력한 종목 코드에 대한 검색 과정에서 에러 발생, 입력값 : " + code);
        }

        return themeByStockCodeResponseDtoList;
    }

    @Override
    public List<Stock> getStockFromStockThemes(List<StockTheme> stockThemes) {
        return stockThemes.stream()
                .map(StockTheme::getStock)
                .toList();
    }
}
