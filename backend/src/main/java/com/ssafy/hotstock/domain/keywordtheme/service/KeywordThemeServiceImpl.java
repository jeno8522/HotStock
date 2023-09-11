package com.ssafy.hotstock.domain.keywordtheme.service;

import com.ssafy.hotstock.domain.keyword.domain.Keyword;
import com.ssafy.hotstock.domain.keywordtheme.domain.KeywordTheme;
import com.ssafy.hotstock.domain.keywordtheme.domain.KeywordThemeRepository;
import com.ssafy.hotstock.domain.theme.domain.Theme;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KeywordThemeServiceImpl implements KeywordThemeService {

    private final KeywordThemeRepository keywordThemeRepository;

    @Override
    public KeywordTheme insertKeywordTheme(Keyword keyword, Theme theme) {
        KeywordTheme keywordTheme = KeywordTheme.builder()
                .keyword(keyword)
                .theme(theme)
                .build();
        return keywordThemeRepository.save(keywordTheme);
    }

    @Override
    public Optional<KeywordTheme> getKeywordThemeById(Long id) {
        return keywordThemeRepository.findById(id);
    }

    @Override
    public List<KeywordTheme> getAllKeywordThemes() {
        return keywordThemeRepository.findAll();
    }

    @Override
    public KeywordTheme updateKeywordTheme(KeywordTheme keywordTheme) {
        // update 로직 작성 (id를 기반으로 데이터를 찾고 해당 데이터를 업데이트)
        if(keywordTheme.getId() == null || !keywordThemeRepository.existsById(keywordTheme.getId())) {
            throw new IllegalArgumentException("KeywordTheme ID is invalid.");
        }
        return keywordThemeRepository.save(keywordTheme);
    }

    @Override
    public void deleteKeywordTheme(Long id) {
        keywordThemeRepository.deleteById(id);
    }
}
