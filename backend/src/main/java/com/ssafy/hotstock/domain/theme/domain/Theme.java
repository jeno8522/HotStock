package com.ssafy.hotstock.domain.theme.domain;


import com.ssafy.hotstock.domain.keywordtheme.domain.KeywordTheme;
import com.ssafy.hotstock.domain.stock.domain.Stock;
import com.ssafy.hotstock.domain.stocktheme.domain.StockTheme;
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
public class Theme {
    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "theme_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "theme", cascade = ALL)
    private List<KeywordTheme> keywordThemes = new ArrayList<>();

    @OneToMany(mappedBy = "theme", cascade = ALL)
    private List<StockTheme> stockThemes = new ArrayList<>();
}
