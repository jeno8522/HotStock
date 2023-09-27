package com.ssafy.hotstock.domain.news.service;

import com.ssafy.hotstock.domain.news.domain.Media;
import com.ssafy.hotstock.domain.news.dto.MediaResponseDto;
import java.util.List;

public interface MediaService {

    Media getMediaByMediaCompanyNum(int mediaCompanyNum);

    void saveMedia(Media media);

    List<MediaResponseDto> getAllMedia();
}
