package com.ssafy.hotstock.domain.stock.service;

import com.ssafy.hotstock.domain.stock.domain.Stock;
import java.util.List;

public interface StockService {

    Stock insertStock(Stock stock);

    List<Stock> insertStockList(List<Stock> stockList);

}
