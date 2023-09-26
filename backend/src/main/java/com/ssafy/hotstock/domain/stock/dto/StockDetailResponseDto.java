package com.ssafy.hotstock.domain.stock.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockDetailResponseDto {
    private String market_sum;
    private String price_now;
    private String price_rate;
    private String price_high;
    private String price_low;
    private String amount;
    private String price_diff;
}
