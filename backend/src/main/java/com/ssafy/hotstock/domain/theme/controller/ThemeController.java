package com.ssafy.hotstock.domain.theme.controller;


import com.ssafy.hotstock.domain.keyword.domain.Keyword;
import com.ssafy.hotstock.domain.keyword.dto.KeywordResponseIdTextDto;
import com.ssafy.hotstock.domain.keywordtheme.dto.KeywordByThemeIdResponseDto;
import com.ssafy.hotstock.domain.keywordtheme.dto.ThemeByKeywordIdResponseDto;
import com.ssafy.hotstock.domain.keywordtheme.service.KeywordThemeService;
import com.ssafy.hotstock.domain.stock.domain.Stock;
import com.ssafy.hotstock.domain.stock.dto.StockResponseIdNameDto;
import com.ssafy.hotstock.domain.stocktheme.dto.StockByThemeIdResponseDto;
import com.ssafy.hotstock.domain.stocktheme.service.StockThemeService;
import com.ssafy.hotstock.domain.theme.domain.Theme;
import com.ssafy.hotstock.domain.theme.dto.ThemeDetailResponseDto;
import com.ssafy.hotstock.domain.theme.service.ThemeService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"*"})
@RequestMapping("/theme")
public class ThemeController {

    private final ThemeService themeService;

    private final KeywordThemeService keywordThemeService;

    private final StockThemeService stockThemeService;
    @GetMapping("/{themeId}")
    public ResponseEntity<?> getKeywordStockByThemeId(@PathVariable Long themeId) {

        Theme theme = themeService.findById(themeId).orElse(null);

        List<KeywordByThemeIdResponseDto> keywordList= keywordThemeService.getKeywordByThemeId(
            themeId);
        List<StockByThemeIdResponseDto> stockList = stockThemeService.getStockByThemeId(
            themeId);

        ThemeDetailResponseDto themeDetailResponseDto=ThemeDetailResponseDto.builder()
            .name(theme.getName())
            .keywordByThemeIdResponseDtoList(keywordList)
            .stockByThemeIdResponseDtoList(stockList)
            .build();

        return new ResponseEntity<>(themeDetailResponseDto, HttpStatus.OK);
    }
}
