package com.ssafy.hotstock.domain.keywordsummary.service;

import com.ssafy.hotstock.domain.keywordsummary.domain.KeywordCheckPoint;
import com.ssafy.hotstock.domain.keywordsummary.domain.KeywordCheckPointRepository;
import com.ssafy.hotstock.domain.keywordsummary.domain.KeywordCountLog;
import com.ssafy.hotstock.domain.keywordsummary.domain.KeywordCountLogRepository;
import com.ssafy.hotstock.domain.keywordsummary.dto.KeywordResponseDto;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class KeywordSummaryServiceImpl implements KeywordSummaryService{

    private final KeywordCheckPointRepository keywordCheckPointRepository;

    private final KeywordCountLogRepository keywordCountLogRepository;

    // 현웅이 파이썬 서버에서 받은 response로 List<KeywordResponseDto> -> 우리 엔티티에 저장하는 로직
    @Override
    public void insertKeywordList(List<KeywordResponseDto> keywordResponseDtoList) {

        for (KeywordResponseDto keywordResponseDto : keywordResponseDtoList) {
            String content = keywordResponseDto.getContent();
            int subCount = keywordResponseDto.getSubCount();

            ZonedDateTime currentTime = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            String createDate = currentTime.format(formatter);

            KeywordCheckPoint keywordCheckPoint = KeywordCheckPoint.builder()
                .checkTime(createDate)
                .build();

            keywordCheckPointRepository.save(keywordCheckPoint);

            KeywordCountLog keywordCountLog = KeywordCountLog.builder()
                .content(content)
                .subCount(subCount)
                .keywordCheckPoint(keywordCheckPoint)
                .build();

            keywordCountLogRepository.save(keywordCountLog);
        }
    }


}
