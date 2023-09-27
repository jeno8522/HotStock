package com.ssafy.hotstock.domain.keyword.service;


import com.ssafy.hotstock.domain.keyword.domain.Keyword;
import com.ssafy.hotstock.domain.keyword.dto.TopKeywordsResponseDto;
import com.ssafy.hotstock.domain.keyword.repository.KeywordRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class KeywordServiceImpl implements KeywordService {

    private final KeywordRepository keywordRepository;

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
    public Keyword findKeywordByContent(String content) {
            return keywordRepository.findByContent(content).orElse(null);

    }

    // count가 0이상인 키워드만 가져온다.
    @Override
    @Cacheable(value = "keywordCache", key = "#root.methodName")
    public List<TopKeywordsResponseDto> getKeywordsByCount() {
        List<Keyword> keywordList = keywordRepository.findKeywordsByCount(
            PageRequest.of(0, 1000));
        if (keywordList.size() == 0) {
            return null;
        }

        List<TopKeywordsResponseDto> topKeywordsResponseDtoList = keywordList.stream()
            .map(keyword -> TopKeywordsResponseDto.builder()
                .id(keyword.getId())
                .text(keyword.getContent())
                .value(keyword.getCount())
                .build())
            .collect(Collectors.toList());

        return topKeywordsResponseDtoList;
    }

    @Override
    public String getKeywordContent(Long keywordId) {
        // 키워드 가져오기
        Keyword keyword = keywordRepository.findById(keywordId).orElse(null);

        String keywordContent = keyword.getContent();

        return keywordContent;
    }

    // 메뉴 캐시 비우기
    @Override
    @CacheEvict(value = "keywordCache", allEntries = true)
    public void clearKeywordCache() {
        // 캐시를 비우는 메서드입니다.
    }

}
