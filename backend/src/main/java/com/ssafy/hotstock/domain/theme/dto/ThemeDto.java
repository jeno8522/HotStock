package com.ssafy.hotstock.domain.theme.dto;

import com.ssafy.hotstock.domain.theme.domain.Theme;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThemeDto {
    private Long id;
    private String name;
    // List<String> keywordContents; 와 같은 필드를 추가할 수 있음

    public static ThemeDto fromEntity(Theme theme) {
        return ThemeDto.builder()
                .id(theme.getId())
                .name(theme.getName())
                // .keywordContents(theme.getKeywordThemes().stream().map(kt -> kt.getKeyword().getContent()).collect(Collectors.toList()))
                .build();
    }
}
