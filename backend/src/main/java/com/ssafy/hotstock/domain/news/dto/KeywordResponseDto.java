package com.ssafy.hotstock.domain.news.dto;

import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KeywordResponseDto {
    // 뉴스에서 추출한 keyword와 이 keyword에 해당하는 theme을 담는 List
    private List<String[]> keywordResults;

}