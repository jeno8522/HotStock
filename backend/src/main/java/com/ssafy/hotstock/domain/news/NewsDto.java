package com.ssafy.hotstock.domain.news;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewsDto {
    private Long id;
    private String title;
    private String summary;
    private String link;

    // 정적 변환 메소드
    public static NewsDto fromEntity(News news) {
        return NewsDto.builder()
                .id(news.getId())
                .title(news.getTitle())
                .summary(news.getSummary())
                .link(news.getLink())
                .build();
    }
}
