package com.ssafy.hotstock.domain.news.service;

import com.ssafy.hotstock.domain.news.domain.Media;
import com.ssafy.hotstock.domain.news.repository.MediaRepository;
import com.ssafy.hotstock.domain.news.dto.MediaResponseDto;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class MediaServiceImpl implements MediaService {

    private final MediaRepository mediaRepository;

    /**
     * 언론사 번호로 언론사 정보 가져오기
     */
    @Override
    public Media getMediaByMediaCompanyNum(int mediaCompanyNum) {
        Optional<Media> mediaOptional = mediaRepository.findByMediaCompanyNum(mediaCompanyNum);

        Media media;

        if (mediaOptional.isPresent()) {
            media=mediaOptional.get();
        } else {
            media = new Media(mediaCompanyNum);
        }
        return media;
    }

    /**
     * 언론사 정보 저장
     */
    @Override
    public void saveMedia(Media media) {
        mediaRepository.save(media);
    }

    /**
     * 모든 언론사 정보 가져오기
     */
    @Override
    public List<MediaResponseDto> getAllMedia() {
        List<Media> mediaList = mediaRepository.findAll();

        List<MediaResponseDto> mediaResponseDtoList = new ArrayList<>();
        for (Media media : mediaList) {
            MediaResponseDto mediaResponseDto = MediaResponseDto.from(media);
            mediaResponseDtoList.add(mediaResponseDto);
        }

        return mediaResponseDtoList;
    }
}
