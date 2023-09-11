package com.ssafy.hotstock;

import com.ssafy.hotstock.domain.keywordsummary.domain.KeywordSummary;
import com.ssafy.hotstock.domain.keywordtheme.domain.KeywordTheme;
import com.ssafy.hotstock.domain.news.domain.News;
import com.ssafy.hotstock.domain.news.dto.KeywordResponseDto;
import com.ssafy.hotstock.domain.news.service.NewsService;
import com.ssafy.hotstock.domain.news.service.NewsServiceImpl;
import com.ssafy.hotstock.domain.stock.domain.Stock;
import com.ssafy.hotstock.domain.theme.domain.Theme;
import com.ssafy.hotstock.domain.keyword.domain.Keyword;
import jakarta.persistence.EntityManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class HotstockApplication implements CommandLineRunner {


//	private final EntityManager em;
//	private final NewsServiceImpl newsService;  // 이 부분을 추가
//
//	public HotstockApplication(EntityManager em, NewsServiceImpl newsService) {
//		this.em = em;
//		this.newsService = newsService;  // 이 부분을 추가
//	}


	public static void main(String[] args) {
		SpringApplication.run(HotstockApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
//
//
//
//		// 더미 KeywordResponseDto 리스트를 생성합니다.
//		List<KeywordResponseDto> keywordResponseDtoList = Arrays.asList(
//				KeywordResponseDto.builder()
//						.keywordContent("Apple")
//						.themeNames(Arrays.asList("Technology", "Smartphones", "Innovation"))
//						.build(),
//
//				KeywordResponseDto.builder()
//						.keywordContent("Tesla")
//						.themeNames(Arrays.asList("Automobiles", "Electric Vehicles", "Innovation"))
//						.build(),
//
//				KeywordResponseDto.builder()
//						.keywordContent("Bitcoin")
//						.themeNames(Arrays.asList("Cryptocurrency", "Finance", "Investment"))
//						.build(),
//
//				KeywordResponseDto.builder()
//						.keywordContent("Netflix")
//						.themeNames(Arrays.asList("Entertainment", "Streaming", "Movies"))
//						.build()
//		);
//
//		News news = new News();
//		news.setTitle("Finance News Title");
////		news.setSummary("This is a summary");
//		news.setLink("http://example.com");
////		em.persist(news);
//		newsService.insertKeywordandThemeList(keywordResponseDtoList, news);


	}
}
