package com.ssafy.hotstock.domain.stock.domain;

import com.ssafy.hotstock.domain.stock.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
