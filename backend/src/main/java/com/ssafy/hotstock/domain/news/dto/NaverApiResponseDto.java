package com.ssafy.hotstock.domain.news.dto;

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
public class NaverApiResponseDto {
    private String lastBuildDate;

    private int total;

    private int start;

    private int display;

    private List<NaverApiItemsResponseDto> items;

}
