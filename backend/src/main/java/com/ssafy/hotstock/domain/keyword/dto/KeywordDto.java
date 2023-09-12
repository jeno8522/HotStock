package com.ssafy.hotstock.domain.keyword.dto;

import com.ssafy.hotstock.domain.keyword.domain.Keyword;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KeywordDto {
    private Long id;
    private String content;
    private LocalDateTime createDate;
    private List<String> keywordThemes;
    private Long newsId;
    private Long keywordSummaryId;
    private Long count;


    public static KeywordDto fromEntity(Keyword keyword) {
        return KeywordDto.builder()
                .id(keyword.getId())
                .content(keyword.getContent())
                .createDate(keyword.getCreateDate())
                // .keywordThemes(keyword.getKeywordThemes()) // 필요한 로직으로 변환
                // .newsId(keyword.getNews().getId()) // Null 체크 등 필요한 로직으로 변환
                // .keywordSummaryId(keyword.getKeywordSummary().getId()) // Null 체크 등 필요한 로직으로 변환
                .build();
    }

}
