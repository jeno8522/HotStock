package com.ssafy.hotstock.domain.keywordsummary.dto;

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
public class KeywordResponseDto {
    // 뉴스에서 추출한 keyword와 이 keyword에 해당하는 theme을 담는 List
    private String content;
    private int subCount;

}