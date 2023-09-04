package com.ssafy.hotstock.domain.keywordsummary;


import com.ssafy.hotstock.domain.keyword.Keyword;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.*;
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

    @OneToOne(mappedBy = "keywordSummary")
    private Keyword keyword;
}
