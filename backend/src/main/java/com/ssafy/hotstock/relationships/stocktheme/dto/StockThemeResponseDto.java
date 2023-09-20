package com.ssafy.hotstock.relationships.stocktheme.dto;

import java.util.List;
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
public class StockThemeResponseDto {

    private String themeName;

    private List<String> stockNames;

}
