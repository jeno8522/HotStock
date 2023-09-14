package com.ssafy.hotstock.domain.keywordtheme.domain;

import com.ssafy.hotstock.domain.keyword.domain.Keyword;
import com.ssafy.hotstock.domain.theme.domain.Theme;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@Table(name = "keyword_theme", indexes = @Index(name = "idx_theme", columnList = "theme"))
public class KeywordTheme {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "keyword_theme_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "keyword_id")
    private Keyword keyword;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theme_id")
    private Theme theme;
}
