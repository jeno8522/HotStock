package com.ssafy.hotstock.domain.keyword.domain;

import com.ssafy.hotstock.domain.keywordsummary.domain.KeywordSummary;
import com.ssafy.hotstock.domain.keywordtheme.domain.KeywordTheme;

import com.ssafy.hotstock.domain.news.domain.News;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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
public class Keyword {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "keyword_id")
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "keyword", cascade = ALL)
    private List<KeywordTheme> keywordThemes = new ArrayList<>();

    @OneToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "news_id")
    private News news;

    @OneToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "keyword_summary_id")
    private KeywordSummary keywordSummary;
}
