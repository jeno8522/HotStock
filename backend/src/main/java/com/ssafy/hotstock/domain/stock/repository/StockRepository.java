package com.ssafy.hotstock.domain.stock.repository;

import com.ssafy.hotstock.domain.stock.domain.Stock;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@Hidden
public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<List<Stock>> findByCode(int code);
    Optional<List<Stock>> findByNameContaining(String name);
}
