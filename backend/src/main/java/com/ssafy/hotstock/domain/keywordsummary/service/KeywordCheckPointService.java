package com.ssafy.hotstock.domain.keywordsummary.service;

import com.ssafy.hotstock.domain.keywordsummary.domain.KeywordCheckPoint;

public interface KeywordCheckPointService {

    Long getLastCheckPointId();

    void insertKeywordCheckPoint(KeywordCheckPoint keywordCheckPoint);
}
