package com.ssafy.hotstock.domain.stock.service;


import com.ssafy.hotstock.domain.stock.domain.Stock;
import com.ssafy.hotstock.domain.stock.repository.StockRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class StockServiceImpl implements StockService{

    private final StockRepository stockRepository;
    @Override
    public Stock insertStock(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public List<Stock> insertStockList(List<Stock> stockList) {
        return stockRepository.saveAll(stockList);
    }
}
