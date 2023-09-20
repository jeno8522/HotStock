package com.ssafy.hotstock.domain.keyword.service;

import com.ssafy.hotstock.domain.keyword.domain.KeywordCheckPoint;

public interface KeywordCheckPointService {

    Long getLastCheckPointId();

    void insertKeywordCheckPoint(KeywordCheckPoint keywordCheckPoint);
}
