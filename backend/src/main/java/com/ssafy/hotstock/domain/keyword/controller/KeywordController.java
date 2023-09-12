package com.ssafy.hotstock.domain.keyword.controller;


import com.ssafy.hotstock.domain.keyword.service.KeywordService;
import com.ssafy.hotstock.domain.keyword.domain.Keyword;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = {"*"})
@RequestMapping("keyword")
public class KeywordController {

//    http://localhost:8080/hotstock/swagger-ui/index.html  -> swagger-ui URL 주소


    @Autowired
    private KeywordService keywordService;


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
    @PostMapping
    public ResponseEntity<Keyword> insertKeyword(@RequestBody Keyword keyword) {
        Keyword createdKeyword = keywordService.insertKeyword(keyword);
        return ResponseEntity.ok(createdKeyword);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Keyword> getKeywordById(@PathVariable Long id) {
        return keywordService.getKeywordById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Keyword>> getAllKeywords() {
        List<Keyword> keywords = keywordService.getAllKeywords();
        return ResponseEntity.ok(keywords);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Keyword> updateKeyword(@PathVariable Long id, @RequestBody Keyword keyword) {
        keyword.setId(id);
        Keyword updatedKeyword = keywordService.updateKeyword(keyword);
        return ResponseEntity.ok(updatedKeyword);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKeyword(@PathVariable Long id) {
        keywordService.deleteKeyword(id);
        return ResponseEntity.noContent().build();
    }
}
