package com.ssafy.hotstock.domain.keywordsummary.domain;


import com.ssafy.hotstock.domain.keyword.domain.Keyword;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KeywordSummary {
    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "keyword_summary_id")
    private Long id;

    @Column(name = "count")
    private Long count;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @OneToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "keyword_id")
    private Keyword keyword;
}
