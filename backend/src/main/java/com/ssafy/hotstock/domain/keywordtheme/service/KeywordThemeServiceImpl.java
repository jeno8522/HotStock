package com.ssafy.hotstock.domain.keywordtheme.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.hotstock.domain.keyword.domain.Keyword;
import com.ssafy.hotstock.domain.keyword.service.KeywordService;
import com.ssafy.hotstock.domain.keywordtheme.domain.KeywordTheme;
import com.ssafy.hotstock.domain.keywordtheme.domain.KeywordThemeRepository;
import com.ssafy.hotstock.domain.keywordtheme.dto.KeywordThemeResponseDto;
import com.ssafy.hotstock.domain.theme.domain.Theme;
import com.ssafy.hotstock.domain.theme.service.ThemeService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KeywordThemeServiceImpl implements KeywordThemeService {

    private final KeywordThemeRepository keywordThemeRepository;

    private final KeywordService keywordService;

    private final ThemeService themeService;
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
    public List<KeywordTheme> getKeywordThemeByKeywordId(Long keywordId) {
        return keywordThemeRepository.findByKeywordId(keywordId);
    }

    @Override
    public List<KeywordTheme> getKeywordThemeByThemeId(Long themeId) {
        return keywordThemeRepository.findByThemeId(themeId);
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


    // 파이썬 서버에 keyword list request -> response로 List<KeywordThemeResponseDto> 받아옴
    // KeywordThemeResponseDto -> list <keyword : list<theme>>
    @Override
    public List<KeywordThemeResponseDto> fetchKeywordTheme(String[] keywords) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://your-python-server.com/extract-keywords"; // Python 서버 URL

        ObjectMapper mapper = new ObjectMapper();
        String requestToJson = mapper.writeValueAsString(keywords);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(requestToJson, headers);

        // HTTP POST 요청 보내기
        ResponseEntity<List<KeywordThemeResponseDto>> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<List<KeywordThemeResponseDto>>() {}
        );


        // KeywordThemeResponseDto : { keyword : [theme] } -> {String : List<String>}
        // Response Body에서 키워드, 관련 theme 리스트 추출
        // 위 내용이 response.getBody() 메소드인데, 맞는지 확인해봐야함
        
//        바로 KeywordTheme에 keyword, theme list 저장하는 메소드 호출할 수도 있음
//        insertKeywordTheme(response.getBody());
        return response.getBody();
        
    }

    @Override
    public void insertKeywordTheme(List<KeywordThemeResponseDto> keywordThemeResponseDtos) {
        for (KeywordThemeResponseDto keywordThemeResponseDto: keywordThemeResponseDtos) {
            // KeywordThemeResponseDto 내에 저장된 keyword의 content로 DB에서 Keyword 불러오기
            String keywordContent = keywordThemeResponseDto.getKeywordContent();
            Keyword keyword = keywordService.findKeywordByContent(keywordContent);

            // 받아온 list<theme>에서 theme 각각에 keyword 붙여서 keywordTheme에 저장
            List<String> themeNames = keywordThemeResponseDto.getThemeNames();
            for (String themeName: themeNames
                 ) {
                Theme theme = themeService.findThemeByName(themeName);
                insertKeywordTheme(keyword, theme);
            }
        }
    }

    @Override
    public List<Keyword> getKeywordFromKeywordThemes(List<KeywordTheme> keywordThemes) {
        return keywordThemes.stream()
                .map(KeywordTheme::getKeyword)
                .toList();
    }


}
