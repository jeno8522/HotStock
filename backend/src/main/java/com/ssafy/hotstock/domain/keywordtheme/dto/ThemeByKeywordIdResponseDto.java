package com.ssafy.hotstock.domain.keywordtheme.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThemeByKeywordIdResponseDto {

    private Long themeId;
    private String name;
}