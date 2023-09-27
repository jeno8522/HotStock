package com.ssafy.hotstock.domain.stock.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockResponseDto {
    private String name;
    private String code;
}
