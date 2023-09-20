package com.ssafy.hotstock.domain.keyword.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.hotstock.domain.keyword.domain.KeywordCheckPoint;
import com.ssafy.hotstock.domain.keyword.domain.KeywordCountLog;
import com.ssafy.hotstock.domain.keyword.dto.KeywordSubCountResponseDto;
import com.ssafy.hotstock.domain.news.dto.NewsResponseDto;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
@Service
public class KeywordSummaryServiceImpl implements KeywordSummaryService {

    private final KeywordCheckPointService keywordCheckPointService;

    private final KeywordCountLogService keywordCountLogService;


    // 현웅이 파이썬 서버에서 받은 response로 List<KeywordResponseDto> -> 우리 엔티티에 저장하는 로직
    @Override
    public void insertKeywordList(List<KeywordSubCountResponseDto> keywordSubCountResponseDtoList) {

        ZonedDateTime currentTime = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String createDate = currentTime.format(formatter);

        KeywordCheckPoint keywordCheckPoint = KeywordCheckPoint.builder()
            .checkTime(createDate)
            .build();

        keywordCheckPointService.insertKeywordCheckPoint(keywordCheckPoint);

        for (KeywordSubCountResponseDto keywordSubCountResponseDto : keywordSubCountResponseDtoList) {
            String keywordContent = keywordSubCountResponseDto.getKeywordContent();
            int subCount = keywordSubCountResponseDto.getNewsIds().size();

            KeywordCountLog keywordCountLog = KeywordCountLog.builder()
                .keywordContent(keywordContent)
                .subCount(subCount)
                .keywordCheckPoint(keywordCheckPoint)
                .build();

            keywordCountLogService.insertKeywordCountLog(keywordCountLog);
        }
    }

    // 파이썬 서버에 뉴스기사 request -> response로 List<KeywordResponseDto> 받아옴
    public List<KeywordSubCountResponseDto> fetchKeywords(
        List<NewsResponseDto> newsResponseDtoList) {

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://hot-stock.shop:5000/keyword/"; // Python 서버 URL

        List<String[]> extractKeywordRequest = new ArrayList();

        for (NewsResponseDto newsResponseDto : newsResponseDtoList) {
            String newsId = String.valueOf(newsResponseDto.getNewsId());
            String title = newsResponseDto.getTitle();
            String content = newsResponseDto.getContent();
            extractKeywordRequest.add(new String[]{newsId, title, content});
        }

        ObjectMapper mapper = new ObjectMapper();
        String requestToJson = null;

        try {
            requestToJson = mapper.writeValueAsString(extractKeywordRequest);
        } catch (JsonProcessingException e) {
            log.error("JSON 매핑 오류: " + e.getCause());
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(requestToJson, headers);

        // HTTP POST 요청 보내기
        ResponseEntity<List<KeywordSubCountResponseDto>> response = restTemplate.exchange(url,
            HttpMethod.POST, entity,
            new ParameterizedTypeReference<List<KeywordSubCountResponseDto>>() {
            });
        // Response Body에서 키워드, 관련 theme 리스트 추출
        List<KeywordSubCountResponseDto> keywordSubCountResponseDtoList = response.getBody();

        //예외 처리 추가해야함유
        if (keywordSubCountResponseDtoList == null) {
            log.error("기사와 관련 키워드가 존재하지 않습니다.");
            return null;
        } else {
            insertKeywordList(keywordSubCountResponseDtoList);
        }

        return keywordSubCountResponseDtoList;
    }

}
