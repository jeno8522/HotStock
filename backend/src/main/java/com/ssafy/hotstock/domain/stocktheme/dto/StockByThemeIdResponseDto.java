package com.ssafy.hotstock.domain.stocktheme.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockByThemeIdResponseDto {
    private int code;
    private String name;
    private String reason;
}
