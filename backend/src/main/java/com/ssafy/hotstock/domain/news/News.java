package com.ssafy.hotstock.domain.news;


import com.ssafy.hotstock.domain.keyword.Keyword;
import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "summary")
    private String summary;

    @Column(name = "link")
    private String link;

    @OneToOne(mappedBy = "news", fetch = LAZY)
    private Keyword keyword;
}
