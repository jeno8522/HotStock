package com.ssafy.hotstock.domain.stocktheme.controller;


import com.ssafy.hotstock.domain.stocktheme.domain.StockTheme;
import com.ssafy.hotstock.domain.stocktheme.dto.StockByThemeIdResponseDto;
import com.ssafy.hotstock.domain.stocktheme.dto.ThemeByStockCodeResponseDto;
import com.ssafy.hotstock.domain.stocktheme.service.StockThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"*"})
@RequestMapping("/stocktheme")
public class StockThemeController {

    private final StockThemeService stockThemeService;

    @GetMapping("theme/{themeId}")
    public ResponseEntity<?> getStockThemeByThemeId(@PathVariable Long themeId) {
        List<StockByThemeIdResponseDto> stockByThemeIdResponseDtos = stockThemeService.getStockByThemeId(themeId);
        return ResponseEntity.ok(stockByThemeIdResponseDtos);
    }

    @GetMapping("stock/{code}")
    public ResponseEntity<?> getStockThemeByCodeName(@PathVariable int code) {
        List<ThemeByStockCodeResponseDto> themeBystockCodeResponseDtoList = stockThemeService.getThemeByStockCode(
            code);
        return new ResponseEntity<>(themeBystockCodeResponseDtoList, HttpStatus.OK);
    }

}
