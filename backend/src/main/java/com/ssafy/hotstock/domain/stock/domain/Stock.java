package com.ssafy.hotstock.domain.stock.domain;

import com.ssafy.hotstock.domain.theme.domain.Theme;
import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "theme_id")
    private Theme theme;

}
