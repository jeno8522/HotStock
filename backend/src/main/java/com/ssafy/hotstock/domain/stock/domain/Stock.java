package com.ssafy.hotstock.domain.stock.domain;

import com.ssafy.hotstock.domain.stocktheme.domain.StockTheme;
import com.ssafy.hotstock.domain.theme.domain.Theme;
import jakarta.persistence.*;
import javax.print.attribute.standard.MediaSize.NA;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.*;
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

    @Column(name = "code")
    private int code;

    @Column(name = "reason", columnDefinition = "TEXT")
    private String reason;

    @OneToMany(mappedBy = "stock", cascade = ALL)
    private List<StockTheme> stockThemes = new ArrayList<>();


}
