package com.ssafy.hotstock.domain.keywordsummary.service;

import com.ssafy.hotstock.domain.keywordsummary.domain.KeywordCheckPoint;
import com.ssafy.hotstock.domain.keywordsummary.repository.KeywordCheckPointRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class KeywordCheckPointSeviceImpl implements KeywordCheckPointService{

    private final KeywordCheckPointRepository keywordCheckPointRepository;
    @Override
    public Long getLastCheckPointId() {
        KeywordCheckPoint keywordCheckPoint=keywordCheckPointRepository.findTopByOrderByIdDesc().orElse(null);
        if (keywordCheckPoint != null) {
            return keywordCheckPoint.getId();
        }
        System.out.println("getLastCheckPointId");
        return 0L;
    }

    @Override
    public void insertKeywordCheckPoint(KeywordCheckPoint keywordCheckPoint) {
        keywordCheckPointRepository.save(keywordCheckPoint);
    }
}
