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
    private Long stockId;
    private String name;
    private int code;
    private String reason;
}
