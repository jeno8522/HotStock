package com.ssafy.hotstock.domain.keyword.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class TopKeywordsResponseDto {
    private Long id;
    private String text;
    private int value;
}
