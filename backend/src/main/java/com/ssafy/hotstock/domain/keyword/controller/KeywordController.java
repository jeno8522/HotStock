package com.ssafy.hotstock.domain.keyword.controller;


import com.ssafy.hotstock.domain.keyword.domain.TopKeywordsResponseDto;
import com.ssafy.hotstock.domain.keyword.service.KeywordService;
import com.ssafy.hotstock.domain.keyword.domain.Keyword;
import com.ssafy.hotstock.domain.keywordtheme.domain.KeywordTheme;
import com.ssafy.hotstock.domain.keywordtheme.service.KeywordThemeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = {"*"})
@RequestMapping("/keyword")
public class KeywordController {

//    http://localhost:8080/api/swagger-ui/index.html  -> swagger-ui URL 주소


    @Autowired
    private KeywordService keywordService;

    private KeywordThemeService keywordThemeService;


    //  POST keyword  swagger 용 더미데이터
//{
//    "keyword": {
//    "content": "Finance Related",
//            "createDate": "2023-09-05T07:28:55.382"
//},
//    "keywordSummary": {
//    "count": 5,
//            "createDate": "2023-09-05T07:28:55.382"
//},
//    "news": {
//    "title": "Finance News Title",
//            "summary": "This is a summary",
//            "link": "http://example.com"
//},
//    "theme": {
//    "name": "Banking"
//},
//    "stock": {
//    "name": "ABC Bank",
//            "content": "Top-tier bank"
//},
//    "keywordTheme": {}
//}


//    Todo: KeywordNews 이거 만들어야함
//    @GetMapping("/{keyword_id}")
//    public ResponseEntity<Keyword> getKeywordThemeNewsById(@PathVariable Long keyword_id) {
//        List<KeywordTheme> keywordThemes= keywordThemeService.getKeywordThemeByKeywordId(keyword_id);
////        List<KewordNews> keywordNews =
////        키워드로 추출한 뉴스 가져오는 로직 필요
//        ResponseEntity keyword = new ResponseEntity<>();
//        return keyword;
//    }



    @GetMapping
    public List<TopKeywordsResponseDto> getTopKeywordsByCount() {
        List<Keyword> keywords = keywordService.getTopKeywordsByCount();

        return keywords.stream()
                .map(keyword -> TopKeywordsResponseDto.builder()
                        .id(keyword.getId())
                        .text(keyword.getContent())
                        .value(keyword.getCount())
                        .build())
                .collect(Collectors.toList());
    }
}
