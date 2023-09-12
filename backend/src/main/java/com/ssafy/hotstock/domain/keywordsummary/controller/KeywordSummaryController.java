package com.ssafy.hotstock.domain.keywordsummary.controller;

import com.ssafy.hotstock.domain.keywordsummary.dto.KeywordResponseDto;
import com.ssafy.hotstock.domain.keywordsummary.service.KeywordSummaryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/keyword/summary")
@RequiredArgsConstructor
public class KeywordSummaryController {

    private final KeywordSummaryService keywordSummaryService;
    @PostMapping
    public ResponseEntity<?> add(@RequestBody List<KeywordResponseDto> keywordResponseDtoList) {

        keywordSummaryService.insertKeywordList(keywordResponseDtoList);

        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
