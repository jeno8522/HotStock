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
public class NewsResponseDto {

    private String title;
    private String content;
    private String link;
    private String date;
    private int mediaCompanyNum;
    private int articleNum;
}
