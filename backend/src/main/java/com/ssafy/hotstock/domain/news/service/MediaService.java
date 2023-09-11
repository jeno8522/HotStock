package com.ssafy.hotstock.domain.news.service;

import com.ssafy.hotstock.domain.news.domain.Media;
import java.util.List;

public interface MediaService {

    Media getMediaByMediaCompanyNum(int mediaCompanyNum);

    Media saveMedia(Media media);

    List<Media> getAllMedia();

}
