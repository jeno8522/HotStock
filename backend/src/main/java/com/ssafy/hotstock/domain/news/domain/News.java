package com.ssafy.hotstock.domain.news.domain;


import com.ssafy.hotstock.domain.keyword.domain.Keyword;
import com.ssafy.hotstock.domain.keywordnews.domain.KeywordNews;
import com.ssafy.hotstock.domain.news.dto.NewsResponseDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class News {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "news_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "link")
    private String link;

    @Column(name = "date")
    private String date;

    @Column(name = "media_company_num")
    private int mediaCompanyNum;

    @Column(name = "article_num")
    private int articleNum;

    @OneToMany(mappedBy = "news", cascade = ALL)
    private List<KeywordNews> keywordNews  = new ArrayList<>();

}
