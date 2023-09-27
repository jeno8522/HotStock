package com.ssafy.hotstock.domain.theme.dto;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThemeResponseDto {
    private Long themeId;
    private String name;
}
