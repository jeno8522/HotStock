package com.ssafy.hotstock.relationships.keywordnews.service;

import com.ssafy.hotstock.domain.keyword.domain.Keyword;
import com.ssafy.hotstock.domain.keyword.service.KeywordService;
import com.ssafy.hotstock.relationships.keywordnews.domain.KeywordNews;
import com.ssafy.hotstock.relationships.keywordnews.repository.KeywordNewsRepository;
import com.ssafy.hotstock.domain.keyword.domain.KeywordCountLog;
import com.ssafy.hotstock.domain.keyword.dto.KeywordSubCountResponseDto;
import com.ssafy.hotstock.domain.keyword.service.KeywordCheckPointService;
import com.ssafy.hotstock.domain.keyword.service.KeywordCountLogService;
import com.ssafy.hotstock.domain.news.domain.News;
import com.ssafy.hotstock.domain.news.service.NewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class KeywordNewsServiceImpl implements KeywordNewsService{

    private final KeywordNewsRepository keywordNewsRepository;
    private final KeywordService keywordService;
    private final KeywordCheckPointService keywordCheckPointService;
    private final KeywordCountLogService keywordCountLogService;
    private final NewsService newsService;


    /**
     * @param keywordSubCountResponseDtoList
     * newsId : 저장 된 뉴스 id
     * keywordContent : 키워드
     * subCount : 키워드 개수
     */
    @Override
    public List<String> insertKeywordNews(List<KeywordSubCountResponseDto> keywordSubCountResponseDtoList) {
        List<String> keywordList = new ArrayList<>();
        Long checkPointId = keywordCheckPointService.getLastCheckPointId();

        // 24시간이 지났으므로 24시간 10분전 키워드의 카운트 줄이기
        if (checkPointId - 3L >= 0L) {
            List<KeywordCountLog> keywordCountLogList = keywordCountLogService.getKeywordCountLogByCheckPointId(
                checkPointId - 3L);

            for (KeywordCountLog keywordCountLog : keywordCountLogList) {
                String keywordContent = keywordCountLog.getKeywordContent();
                int subCount = keywordCountLog.getSubCount();

                Keyword keyword = keywordService.findKeywordByContent(
                    keywordContent);

                if (keyword!=null) {
                    keyword.setCount(keyword.getCount() - subCount);
                    keywordService.insertKeyword(keyword);
                } else {
                    log.debug("해당하는 키워드가 존재하지 않습니다.(불가능)");
                }
            }
        }

        // 새로 들어온 키워드들을 처리하는 로직
        for (KeywordSubCountResponseDto keywordSubCountResponseDto : keywordSubCountResponseDtoList) {

            String keywordContent = keywordSubCountResponseDto.getKeywordContent();
            int subCount = keywordSubCountResponseDto.getNewsIds().size();

            Keyword keyword = keywordService.findKeywordByContent(
                keywordContent);

            if (keyword!=null) {
                keyword.setCount(keyword.getCount() + subCount);
                keywordService.insertKeyword(keyword);
            } else {

                keyword = Keyword.builder()
                .content(keywordContent)
                .count(subCount)
                .build();
                keywordService.insertKeyword(keyword);

                List<KeywordNews> keywordNewsList = new ArrayList<>();
                for (Long newsId : keywordSubCountResponseDto.getNewsIds()) {
                    News news = newsService.findNewsById(newsId);
                    if (news ==null) {
                        log.debug("해당 newsId 값과 일치하는 뉴스가 존재하지 않습니다.");
                    }

                    KeywordNews keywordNews= KeywordNews.builder()
                        .news(news)
                        .keyword(keyword)
                        .build();
                    keywordNewsList.add(keywordNews);
                }
                insertKeywordNewsList(keywordNewsList);

                keywordList.add(keywordContent);
            }
        }
        return keywordList;
    }

    @Override
    public List<KeywordNews> getKeywordNewsByKeywordId(Long keywordId) {
        return keywordNewsRepository.findByKeywordId(keywordId);
    }

    @Override
    public List<KeywordNews> getKeywordNewsByNewsId(Long newsId) {
        return keywordNewsRepository.findByNewsId(newsId);
    }


    @Override
    public List<KeywordNews> insertKeywordNewsList(List<KeywordNews> keywordNewsList) {
        return keywordNewsRepository.saveAll(keywordNewsList);
    }

}
