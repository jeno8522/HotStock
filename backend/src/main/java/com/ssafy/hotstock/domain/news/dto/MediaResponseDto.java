package com.ssafy.hotstock.domain.news.dto;

import com.ssafy.hotstock.domain.news.domain.Media;
import jakarta.persistence.Column;
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
public class MediaResponseDto {

    private int mediaCompanyNum;

    private int currArticleNum;

    public static MediaResponseDto from(Media media) {
        return MediaResponseDto.builder()
            .mediaCompanyNum(media.getMediaCompanyNum())
            .currArticleNum(media.getCurrArticleNum())
            .build();
    }
}
