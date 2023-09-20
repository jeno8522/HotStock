package com.ssafy.hotstock.domain.stock.domain;

import com.ssafy.hotstock.relationships.stocktheme.domain.StockTheme;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.GenerationType.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Stock {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "stock_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "content")
    private String content;

    @OneToMany(mappedBy = "stock", cascade = ALL)
    private List<StockTheme> stockThemes = new ArrayList<>();


}
