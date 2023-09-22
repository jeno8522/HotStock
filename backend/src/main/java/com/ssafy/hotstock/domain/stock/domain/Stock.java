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

    @Id @Column(name = "code")
    private int code;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "stock", cascade = ALL)
    private List<StockTheme> stockThemes = new ArrayList<>();


}
