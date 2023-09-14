package com.ssafy.hotstock.domain.keyword.service;


import com.ssafy.hotstock.domain.keyword.domain.Keyword;
import com.ssafy.hotstock.domain.keyword.repository.KeywordRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class KeywordServiceImpl implements KeywordService {

    private final KeywordRepository keywordRepository;

//    @Autowired
//    private NewsService newsService;




    // 삽입 메소드

    /**
     *

     * inputKeyword가 이미 존재하면 count값만 증가 시킴
     * 존재하지 않다면 DB 테이블에 초기화

     *
     */
    @Override
    public Keyword insertKeyword(Keyword inputKeyword) {
        // 최종 변경된 keyword를 저장하고 반환
        return keywordRepository.save(inputKeyword);
    }


    @Override
    public Optional<Keyword> getKeywordById(Long id) {
        return keywordRepository.findById(id);
    }

    @Override
    public List<Keyword> getAllKeywords() {
        return keywordRepository.findAll();
    }

    @Override
    public Keyword updateKeyword(Keyword keyword) {

        return keywordRepository.save(keyword);
    }

    @Override
    public void deleteKeyword(Long id) {
        Keyword existingKeyword = keywordRepository.findById(id).orElse(null);
        if (existingKeyword != null) {
//            newsService.deleteNews(existingKeyword.getNews().getId());
        }

        keywordRepository.deleteById(id);
    }

    @Override
    public Keyword findKeywordByContent(String content) {
            return keywordRepository.findByContent(content).orElse(null);

    }
    

    
    // count 기준 상위 20 개 혹은 그 이하의 키워드 들을 DB에서 가져오는 메소드
    @Override
    public List<Keyword> getTopKeywordsByCount() {
        return keywordRepository.findTopKeywordsByCount(PageRequest.of(0, 20));
    }
}
