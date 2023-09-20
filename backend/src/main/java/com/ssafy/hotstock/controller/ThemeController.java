package com.ssafy.hotstock.controller;


import com.ssafy.hotstock.domain.keyword.domain.Keyword;
import com.ssafy.hotstock.domain.keyword.dto.KeywordResponseIdTextDto;
import com.ssafy.hotstock.relationships.keywordtheme.domain.KeywordTheme;
import com.ssafy.hotstock.relationships.keywordtheme.service.KeywordThemeService;
import com.ssafy.hotstock.domain.stock.domain.Stock;
import com.ssafy.hotstock.domain.stock.dto.StockResponseIdNameDto;
import com.ssafy.hotstock.relationships.stocktheme.domain.StockTheme;
import com.ssafy.hotstock.relationships.stocktheme.service.StockThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"*"})
@RequestMapping("/theme")
public class ThemeController {

    private final KeywordThemeService keywordThemeService;

    private final StockThemeService stockThemeService;
    @GetMapping("/{theme_id}")
    public Map<String, Object> getKeywordStockByThemeId(@PathVariable Long theme_id) {
         List<KeywordTheme> keywordThemes = keywordThemeService.getKeywordThemeByThemeId(theme_id);
         List<StockTheme> stockThemes = stockThemeService.findStockThemesByThemeId(theme_id);

         List<Keyword> keywords = keywordThemeService.getKeywordFromKeywordThemes(keywordThemes);
         List<Stock> stocks = stockThemeService.getStockFromStockThemes(stockThemes);

        List<KeywordResponseIdTextDto> keywordResponseIdTextDtos = keywords.stream()
                .map(keyword -> new KeywordResponseIdTextDto(keyword.getId(), keyword.getContent()))
                .toList();

        List<StockResponseIdNameDto> stockResponseIdNameDtos = stocks.stream()
                .map(stock -> new StockResponseIdNameDto(stock.getId(), stock.getName()))
                .toList();

        Map<String, Object> response = new HashMap<>();
        response.put("keyword", keywordResponseIdTextDtos);
        response.put("stock", stockResponseIdNameDtos);

        return response;
    }
}
