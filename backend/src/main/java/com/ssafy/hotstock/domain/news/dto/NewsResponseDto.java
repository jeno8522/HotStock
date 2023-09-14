package com.ssafy.hotstock.domain.news.dto;

import com.ssafy.hotstock.domain.news.domain.News;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsResponseDto {

    private Long newsId;
    private String title;
    private String content;

    public static NewsResponseDto from(News news) {
        return NewsResponseDto.builder()
            .newsId(news.getId())
            .title(news.getTitle())
            .content(news.getContent())
            .build();
    }
}
