package com.ssafy.hotstock.domain.keyword;


import com.ssafy.hotstock.domain.keywordsummary.KeywordSummaryService;
import com.ssafy.hotstock.domain.news.News;
import com.ssafy.hotstock.domain.keywordsummary.KeywordSummary;

import com.ssafy.hotstock.domain.news.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KeywordServiceImpl implements KeywordService {

    @Autowired
    private KeywordRepository keywordRepository;

    @Autowired
    private NewsService newsService;

    @Autowired
    private KeywordSummaryService keywordSummaryService;

    @Override
    public Keyword createKeyword(Keyword keyword) {
        News news = newsService.createNews(keyword.getNews());
        KeywordSummary keywordSummary = keywordSummaryService.createKeywordSummary(keyword.getKeywordSummary());
        keyword.setNews(news);
        keyword.setKeywordSummary(keywordSummary);

        return keywordRepository.save(keyword);
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

        News updatedNews = newsService.updateNews(keyword.getNews());
        KeywordSummary updatedKeywordSummary = keywordSummaryService.updateKeywordSummary(keyword.getKeywordSummary());
        keyword.setNews(updatedNews);
        keyword.setKeywordSummary(updatedKeywordSummary);

        return keywordRepository.save(keyword);
    }

    @Override
    public void deleteKeyword(Long id) {
        Keyword existingKeyword = keywordRepository.findById(id).orElse(null);
        if (existingKeyword != null) {
            newsService.deleteNews(existingKeyword.getNews().getId());
            keywordSummaryService.deleteKeywordSummary(existingKeyword.getKeywordSummary().getId());
        }

        keywordRepository.deleteById(id);
    }

}
