package com.ssafy.hotstock.domain.keyword.domain;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.IDENTITY;

import com.ssafy.hotstock.domain.keywordnews.domain.KeywordNews;
import com.ssafy.hotstock.domain.keywordtheme.domain.KeywordTheme;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;

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

    @Column(name = "count")
    private int count;

    @OneToMany(mappedBy = "keyword", cascade = ALL)
    private List<KeywordTheme> keywordThemes = new ArrayList<>();

    @OneToMany(mappedBy = "keyword", cascade = ALL)
    private List<KeywordNews> keywordNews  = new ArrayList<>();

    public Keyword(String content, int count) {
        this.content = content;
        this.count = count;
    }

    //    @OneToOne(mappedBy = "keyword")
//    private KeywordSummary keywordSummary;
}
