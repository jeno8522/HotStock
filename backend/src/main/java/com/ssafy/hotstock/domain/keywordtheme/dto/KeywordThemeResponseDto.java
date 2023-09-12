package com.ssafy.hotstock.domain.keywordtheme.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KeywordThemeResponseDto {
    // 뉴스에서 추출한 keyword와 이 keyword에 해당하는 theme을 담는 List
    private String keyword;
    private int subCount;

}