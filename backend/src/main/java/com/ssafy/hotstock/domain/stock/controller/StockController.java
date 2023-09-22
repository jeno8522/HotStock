package com.ssafy.hotstock.domain.stock.controller;


import com.ssafy.hotstock.domain.stock.dto.StockByCodeNameResponseDto;
import com.ssafy.hotstock.domain.stock.service.StockService;
import com.ssafy.hotstock.global.advice.exception.StockFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"*"})
@RequestMapping("/stock")
public class StockController {

    private final StockService stockService;

    @GetMapping("/{codeName}")
    public ResponseEntity<?> getStock(@PathVariable String codeName) {
        try {
            List<StockByCodeNameResponseDto> stockByCodeNameResponseDtos = stockService.findStocksByCodeName(codeName);
            return new ResponseEntity<>(stockByCodeNameResponseDtos, HttpStatus.OK);
        } catch (StockFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);  // 404 Not Found, Stock 검색 결과 없음
        }
    }
}
