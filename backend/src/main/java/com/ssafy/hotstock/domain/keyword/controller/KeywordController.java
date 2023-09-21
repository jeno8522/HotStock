package com.ssafy.hotstock.domain.keyword.controller;


import com.ssafy.hotstock.domain.keyword.dto.KeywordDetailResponseDto;
import com.ssafy.hotstock.domain.keyword.dto.TopKeywordsResponseDto;
import com.ssafy.hotstock.domain.keyword.service.KeywordService;
import com.ssafy.hotstock.domain.keyword.domain.Keyword;
import com.ssafy.hotstock.domain.keywordnews.dto.NewsByKeywordIdResponseDto;
import com.ssafy.hotstock.domain.keywordnews.service.KeywordNewsService;
import com.ssafy.hotstock.domain.keywordtheme.domain.KeywordTheme;
import com.ssafy.hotstock.domain.keywordtheme.dto.ThemeByKeywordIdResponseDto;
import com.ssafy.hotstock.domain.keywordtheme.service.KeywordThemeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"*"})
@RequestMapping("/keyword")
public class KeywordController {

//    http://localhost:8080/api/swagger-ui/index.html  -> swagger-ui URL 주소


    @Autowired
    private KeywordService keywordService;

    @Autowired
    private KeywordThemeService keywordThemeService;

    @Autowired
    private KeywordNewsService keywordNewsService;

    private static final Logger log = LoggerFactory.getLogger(KeywordController.class);


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
    @GetMapping("/{keywordId}")
    public ResponseEntity<?> getKeywordThemeNewsById(@PathVariable Long keywordId) {
        String keywordContent = keywordService.getKeywordContent(keywordId);

        List<ThemeByKeywordIdResponseDto> themeList = keywordThemeService.getThemeByKeywordIdWithTheme(
            keywordId);

        List<NewsByKeywordIdResponseDto> newsList = keywordNewsService.getNewsByKeywordIdWithNews(
            keywordId);

        KeywordDetailResponseDto keywordDetailResponseDto= KeywordDetailResponseDto.builder()
            .keywordContent(keywordContent)
            .themeByKeywordIdResponseDtoList(themeList)
            .newsByKeywordIdResponseDtoList(newsList)
            .build();

        return new ResponseEntity<>(keywordDetailResponseDto, HttpStatus.OK);
    }



    @GetMapping
    public List<TopKeywordsResponseDto> getTopKeywordsByCount() {
        List<Keyword> keywords = keywordService.getTopKeywordsByCount();
//        System.out.println("!!!!!!!!!!!!!!!!topkeywords = " + keywords.toString());

        List<TopKeywordsResponseDto> response = keywords.stream()
                .map(keyword -> TopKeywordsResponseDto.builder()
                        .id(keyword.getId())
                        .text(keyword.getContent())
                        .value(keyword.getCount())
                        .build())
                .collect(Collectors.toList());

//        log.info("top 키워드들이에요!!! : {}", response);
//        TopKeywordsResponseDto dummy = TopKeywordsResponseDto.builder()
//                .id(1L)
//                .text("hihihi")
//                .value(100L)
//                .build();
//        response.add(dummy);
        return response;

    }

}
