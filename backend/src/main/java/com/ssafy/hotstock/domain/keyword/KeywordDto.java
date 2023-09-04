package com.ssafy.hotstock.domain.keyword;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KeywordDto {
    private Long id;
    private String content;
    private LocalDateTime createDate;

    // 정적 변환 메소드
    public static KeywordDto fromEntity(Keyword keyword) {
        return KeywordDto.builder()
                .id(keyword.getId())
                .content(keyword.getContent())
                .createDate(keyword.getCreateDate())
                .build();
    }
}
