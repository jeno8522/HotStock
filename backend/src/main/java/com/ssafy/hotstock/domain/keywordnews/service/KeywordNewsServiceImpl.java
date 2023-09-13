package com.ssafy.hotstock.domain.keywordnews.service;

import com.ssafy.hotstock.domain.keyword.domain.Keyword;
import com.ssafy.hotstock.domain.keywordnews.domain.KeywordNews;
import com.ssafy.hotstock.domain.keywordnews.repository.KeywordNewsRepository;
import com.ssafy.hotstock.domain.news.domain.News;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KeywordNewsServiceImpl implements KeywordNewsService{

    private final KeywordNewsRepository keywordNewsRepository;

    @Override
    public void insertKeywordNews(Keyword keyword, News news) {
        KeywordNews keywordNews = KeywordNews.builder()
                .keyword(keyword)
                .news(news)
                .build();
        keywordNewsRepository.save(keywordNews);
    }

    @Override
    public List<KeywordNews> getKeywordNewsByKeywordId(Long keywordId) {
        return keywordNewsRepository.findByKeywordId(keywordId);
    }

    @Override
    public List<KeywordNews> getKeywordNewsByNewsId(Long newsId) {
        return keywordNewsRepository.findByNewsId(newsId);
    }
}
