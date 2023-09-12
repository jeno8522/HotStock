package com.ssafy.hotstock.domain.keyword.domain;

import com.ssafy.hotstock.domain.keywordnews.domain.KeywordNews;
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
@Table(name = "keyword", indexes = @Index(name = "idx_keyword_content", columnList = "content"))
public class Keyword {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "keyword_id")
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "count")
    private Long count;

    @OneToMany(mappedBy = "keyword", cascade = ALL)
    private List<KeywordTheme> keywordThemes = new ArrayList<>();

    @OneToMany(mappedBy = "keyword", cascade = ALL)
    private List<KeywordNews> keywordNews  = new ArrayList<>();


//    @OneToOne(mappedBy = "keyword")
//    private KeywordSummary keywordSummary;
}
