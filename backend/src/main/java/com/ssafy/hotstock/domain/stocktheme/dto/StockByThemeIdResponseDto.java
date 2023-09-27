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
    private String code;
    private String name;
    private String reason;
    private String market_sum;
    private String price_now;
    private String price_rate;
    private String price_high;
    private String price_low;
    private String amount;
    private String price_diff;
}
