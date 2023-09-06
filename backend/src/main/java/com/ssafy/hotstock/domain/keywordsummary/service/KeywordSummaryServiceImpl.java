package com.ssafy.hotstock.domain.keywordsummary.service;



import com.ssafy.hotstock.domain.keywordsummary.domain.KeywordSummary;
import com.ssafy.hotstock.domain.keywordsummary.domain.KeywordSummaryRepository;
import com.ssafy.hotstock.domain.keywordsummary.service.KeywordSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KeywordSummaryServiceImpl implements KeywordSummaryService {

    @Autowired
    private KeywordSummaryRepository keywordSummaryRepository;

    @Override
    public KeywordSummary createKeywordSummary(KeywordSummary keywordSummary) {
        return keywordSummaryRepository.save(keywordSummary);
    }

    @Override
    public Optional<KeywordSummary> getKeywordSummaryById(Long id) {
        return keywordSummaryRepository.findById(id);
    }

    @Override
    public List<KeywordSummary> getAllKeywordSummary() {
        return keywordSummaryRepository.findAll();
    }

    @Override
    public KeywordSummary updateKeywordSummary(KeywordSummary keywordSummary) {
        return keywordSummaryRepository.save(keywordSummary);
    }

    @Override
    public void deleteKeywordSummary(Long id) {
        keywordSummaryRepository.deleteById(id);
    }
}