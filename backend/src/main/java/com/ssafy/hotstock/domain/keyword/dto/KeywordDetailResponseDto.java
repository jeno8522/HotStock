package com.ssafy.hotstock.domain.keyword.dto;

import com.ssafy.hotstock.domain.keywordnews.dto.NewsByKeywordIdResponseDto;
import com.ssafy.hotstock.domain.keywordtheme.dto.ThemeByKeywordIdResponseDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KeywordDetailResponseDto {
    private String keywordContent;

    private List<ThemeByKeywordIdResponseDto> themeByKeywordIdResponseDtoList;

    private List<NewsByKeywordIdResponseDto> newsByKeywordIdResponseDtoList;

}
