package com.ssafy.hotstock.domain.stock.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockResponseIdNameDto {
    private Long id;
    private String name;
}
