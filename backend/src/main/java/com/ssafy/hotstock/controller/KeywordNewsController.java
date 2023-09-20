package com.ssafy.hotstock.controller;

import com.ssafy.hotstock.relationships.keywordnews.service.KeywordNewsService;
import com.ssafy.hotstock.domain.keyword.dto.KeywordSubCountResponseDto;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//FIXME : keyword 컨트롤러 밑으로 가야함.
@Slf4j
@RestController
@RequestMapping("/keyword/news")
@RequiredArgsConstructor
public class KeywordNewsController {

    private final KeywordNewsService keywordNewsService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody List<KeywordSubCountResponseDto> keywordSubCountResponseDtoList) {

        keywordNewsService.insertKeywordNews(keywordSubCountResponseDtoList);

        return new ResponseEntity<>("", HttpStatus.OK);
    }

}
