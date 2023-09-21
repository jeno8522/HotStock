package com.ssafy.hotstock.domain.keyword.service;


import com.ssafy.hotstock.domain.keyword.domain.Keyword;
import com.ssafy.hotstock.domain.keyword.dto.KeywordDetailResponseDto;
import com.ssafy.hotstock.domain.keywordtheme.dto.ThemeByKeywordIdResponseDto;
import com.ssafy.hotstock.domain.keyword.repository.KeywordRepository;
import com.ssafy.hotstock.domain.keywordnews.service.KeywordNewsService;
import com.ssafy.hotstock.domain.keywordtheme.service.KeywordThemeService;
import com.ssafy.hotstock.domain.theme.domain.Theme;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
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

    // count 기준 상위 20 개 혹은 그 이하의 키워드 들을 DB에서 가져오는 메소드
    @Override
    public List<Keyword> getTopKeywordsByCount() {
        return keywordRepository.findTopKeywordsByCount(PageRequest.of(0, 20));
    }

    @Override
    public String getKeywordContent(Long keywordId) {
        // 키워드 가져오기
        Keyword keyword = keywordRepository.findById(keywordId).orElse(null);

        String keywordContent = keyword.getContent();

        return keywordContent;
    }
}
