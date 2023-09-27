package com.ssafy.hotstock.domain.theme.dto;

import com.ssafy.hotstock.domain.keywordtheme.dto.KeywordByThemeIdResponseDto;
import com.ssafy.hotstock.domain.stocktheme.dto.StockByThemeIdResponseDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThemeDetailResponseDto {
    private String name;

    private List<KeywordByThemeIdResponseDto> keywordByThemeIdResponseDtoList;
    private List<StockByThemeIdResponseDto> stockByThemeIdResponseDtoList;

}
