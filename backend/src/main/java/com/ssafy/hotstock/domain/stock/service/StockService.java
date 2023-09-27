package com.ssafy.hotstock.domain.stock.service;

import com.ssafy.hotstock.domain.stock.domain.Stock;
import com.ssafy.hotstock.domain.stock.dto.StockByCodeNameResponseDto;
import com.ssafy.hotstock.domain.stock.dto.StockResponseDto;

import java.util.List;

public interface StockService {

    Stock insertStock(Stock stock);

    List<Stock> insertStockList(List<Stock> stockList);

    List<StockByCodeNameResponseDto> findStocksByCodeName(String codeName);

    List<StockByCodeNameResponseDto> getStockDetailsFromPython(int code);

    List<StockResponseDto> getAllStocks();

}
