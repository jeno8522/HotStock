package com.ssafy.hotstock.domain.keywordsummary.dto;

import com.ssafy.hotstock.domain.keywordsummary.domain.KeywordSummary;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KeywordSummaryDto {
    private Long id;
    private Long count;
    private LocalDateTime createDate;

    // 정적 변환 메소드
    public static KeywordSummaryDto fromEntity(KeywordSummary keywordSummary) {
        return KeywordSummaryDto.builder()
                .id(keywordSummary.getId())
                .count(keywordSummary.getCount())
                .createDate(keywordSummary.getCreateDate())
                .build();
    }
}
