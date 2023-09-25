package com.ssafy.hotstock.domain.news.dto;

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
public class NaverApiItemsResponseDto {
    private String title;

    private String originallink;

    private String link;

    private String description;

    private String pubDate;

}
