package com.ssafy.hotstock.domain.theme;


import com.ssafy.hotstock.domain.keywordtheme.KeywordTheme;
import com.ssafy.hotstock.domain.stock.Stock;
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
    private List<Stock> stocks = new ArrayList<>();
}
