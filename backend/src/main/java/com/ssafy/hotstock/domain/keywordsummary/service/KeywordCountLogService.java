package com.ssafy.hotstock.domain.keywordsummary.service;

import com.ssafy.hotstock.domain.keywordsummary.domain.KeywordCountLog;
import java.util.List;

public interface KeywordCountLogService {
    List<KeywordCountLog> getKeywordCountLogByCheckPointId(Long checkPointId);

    void insertKeywordCountLog(KeywordCountLog keywordCountLog);
}
