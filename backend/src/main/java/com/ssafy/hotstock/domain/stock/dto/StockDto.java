package com.ssafy.hotstock.domain.stock.dto;

import com.ssafy.hotstock.domain.stock.domain.Stock;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockDto {
    private Long id;
    private String name;
    private String content;
    private Long themeId;
    
}
