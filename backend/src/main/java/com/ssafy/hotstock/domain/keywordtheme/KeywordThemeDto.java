package com.ssafy.hotstock.domain.keywordtheme;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KeywordThemeDto {
    private Long id;
    private Long keywordId;
    private Long themeId;

    // 정적 변환 메소드
    public static KeywordThemeDto fromEntity(KeywordTheme keywordTheme) {
        return KeywordThemeDto.builder()
                .id(keywordTheme.getId())
                .keywordId(keywordTheme.getKeyword().getId())
                .themeId(keywordTheme.getTheme().getId())
                .build();
    }
}
