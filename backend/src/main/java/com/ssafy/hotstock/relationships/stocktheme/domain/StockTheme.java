package com.ssafy.hotstock.relationships.stocktheme.domain;


import com.ssafy.hotstock.domain.stock.domain.Stock;
import com.ssafy.hotstock.domain.theme.domain.Theme;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockTheme {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_theme_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "theme_id")
    private Theme theme;
}