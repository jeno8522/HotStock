package com.ssafy.hotstock.domain.stock;

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

    public static StockDto fromEntity(Stock stock) {
        return StockDto.builder()
                .id(stock.getId())
                .name(stock.getName())
                .content(stock.getContent())
                .themeId(stock.getTheme() != null ? stock.getTheme().getId() : null) // Theme이 null인 경우를 대비
                .build();
    }
}
