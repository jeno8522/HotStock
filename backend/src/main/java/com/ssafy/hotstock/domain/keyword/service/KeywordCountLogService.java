package com.ssafy.hotstock.domain.keyword.service;

import com.ssafy.hotstock.domain.keyword.domain.KeywordCountLog;

import java.util.List;

public interface KeywordCountLogService {
    List<KeywordCountLog> getKeywordCountLogByCheckPointId(Long checkPointId);

    void insertKeywordCountLog(KeywordCountLog keywordCountLog);
}
