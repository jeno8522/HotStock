package com.ssafy.hotstock.domain.news.dto;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KeywordRequestDto {
    private String title;
    private String content;


}
