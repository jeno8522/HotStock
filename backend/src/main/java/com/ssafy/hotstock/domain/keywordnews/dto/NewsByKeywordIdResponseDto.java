package com.ssafy.hotstock.domain.keywordnews.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewsByKeywordIdResponseDto {

    private Long newsId;

    private String title;

    private String content;

    private String summaryContent;

    private String link;

    private String date;

    private int mediaCompanyNum;

}