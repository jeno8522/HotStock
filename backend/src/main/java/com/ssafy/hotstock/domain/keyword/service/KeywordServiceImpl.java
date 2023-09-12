package com.ssafy.hotstock.domain.keyword.service;


import com.ssafy.hotstock.domain.keyword.domain.Keyword;
import com.ssafy.hotstock.domain.keyword.domain.KeywordRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KeywordServiceImpl implements KeywordService {

    @Autowired
    private KeywordRepository keywordRepository;

//    @Autowired
//    private NewsService newsService;




    // 삽입 메소드

    /**
     *
     * @param inputKeyword
     * inputKeyword가 이미 존재하면 count값만 증가 시킴
     * 존재하지 않다면 DB 테이블에 초기화
     * @return
     *
     */
    @Override
    @Transactional
    public Keyword insertKeyword(Keyword inputKeyword) {
        String content = inputKeyword.getContent();

        // 이미 들어온 keyword가 DB에 존재하는 지 확인
        Keyword existingKeyword = keywordRepository.findByContent(content).orElseGet(() -> {
            Keyword newKeyword = new Keyword();
            newKeyword.setContent(content);
            newKeyword.setCount(0L);  // 초기값 설정
            return keywordRepository.save(newKeyword);
        });

        // count 증가하는 부분 -> 10분단위로 count한 값을 한번에 더해줘야할 것 같음
        existingKeyword.setCount(existingKeyword.getCount() + 1);

        // 최종 변경된 keyword를 저장하고 반환
        return keywordRepository.save(existingKeyword);
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

}
