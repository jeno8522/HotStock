package com.ssafy.hotstock.domain.stocktheme.controller;


import com.ssafy.hotstock.domain.stocktheme.domain.StockTheme;
import com.ssafy.hotstock.domain.stocktheme.dto.StockByThemeIdResponseDto;
import com.ssafy.hotstock.domain.stocktheme.service.StockThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"*"})
@RequestMapping("/stocktheme")
public class StockThemeController {

    private final StockThemeService stockThemeService;

    @GetMapping("/{themeId}")
    public ResponseEntity<?> getStockThemeByThemeId(@PathVariable Long themeId) {
        List<StockByThemeIdResponseDto> stockByThemeIdResponseDtos = stockThemeService.getStockByThemeId(themeId);
        return ResponseEntity.ok(stockByThemeIdResponseDtos);
    }

}
